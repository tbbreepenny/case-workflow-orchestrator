package com.breanna.workflow.repository;

import com.breanna.workflow.model.CaseRecord;
import com.breanna.workflow.model.CaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseRecordRepository extends JpaRepository<CaseRecord, Long> {

    List<CaseRecord> findByStatus(CaseStatus status);

    List<CaseRecord> findByCounty(String county);
}
