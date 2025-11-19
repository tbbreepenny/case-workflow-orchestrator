package com.breanna.workflow.repository;

import com.breanna.workflow.model.CaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseHistoryRepository extends JpaRepository<CaseHistory, Long> {

    List<CaseHistory> findByCaseIdOrderByChangedAtAsc(Long caseId);
}
