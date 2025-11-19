package com.breanna.workflow.service;

import com.breanna.workflow.model.CaseHistory;
import com.breanna.workflow.model.CaseRecord;
import com.breanna.workflow.model.CaseStatus;
import com.breanna.workflow.repository.CaseHistoryRepository;
import com.breanna.workflow.repository.CaseRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CaseWorkflowService {

    private final CaseRecordRepository caseRecordRepository;
    private final CaseHistoryRepository caseHistoryRepository;

    public CaseWorkflowService(CaseRecordRepository caseRecordRepository,
                               CaseHistoryRepository caseHistoryRepository) {
        this.caseRecordRepository = caseRecordRepository;
        this.caseHistoryRepository = caseHistoryRepository;
    }

    @Transactional
    public CaseRecord createCase(String externalCaseId,
                                 String sourceSystem,
                                 String defendantName,
                                 String county) {

        CaseRecord record = new CaseRecord(
                externalCaseId,
                sourceSystem,
                defendantName,
                county,
                CaseStatus.INTAKE
        );

        CaseRecord saved = caseRecordRepository.save(record);

        CaseHistory history = new CaseHistory(
                saved.getId(),
                null,
                CaseStatus.INTAKE,
                "system",
                "Case created in INTAKE."
        );
        caseHistoryRepository.save(history);

        return saved;
    }

    @Transactional
    public CaseRecord transitionCase(Long id,
                                     CaseStatus nextStatus,
                                     String actor,
                                     String note) {

        CaseRecord record = caseRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Case not found: " + id));

        CaseStatus from = record.getStatus();

        if (from == CaseStatus.CLOSED) {
            throw new IllegalStateException("Cannot transition a CLOSED case.");
        }

        record.setStatus(nextStatus);
        CaseRecord updated = caseRecordRepository.save(record);

        CaseHistory history = new CaseHistory(
                updated.getId(),
                from,
                nextStatus,
                actor,
                note
        );
        caseHistoryRepository.save(history);

        return updated;
    }

    public List<CaseRecord> getAllCases() {
        return caseRecordRepository.findAll();
    }

    public List<CaseRecord> getCasesByStatus(CaseStatus status) {
        return caseRecordRepository.findByStatus(status);
    }

    public List<CaseRecord> getCasesByCounty(String county) {
        return caseRecordRepository.findByCounty(county);
    }

    public List<CaseHistory> getCaseHistory(Long caseId) {
        return caseHistoryRepository.findByCaseIdOrderByChangedAtAsc(caseId);
    }
}
