package com.breanna.workflow.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class CaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long caseId;

    @Enumerated(EnumType.STRING)
    private CaseStatus fromStatus;

    @Enumerated(EnumType.STRING)
    private CaseStatus toStatus;

    private String changedBy;
    private OffsetDateTime changedAt;
    private String note;

    public CaseHistory() {}

    public CaseHistory(Long caseId,
                       CaseStatus fromStatus,
                       CaseStatus toStatus,
                       String changedBy,
                       String note) {

        this.caseId = caseId;
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.changedBy = changedBy;
        this.note = note;
        this.changedAt = OffsetDateTime.now();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public CaseStatus getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(CaseStatus fromStatus) {
        this.fromStatus = fromStatus;
    }

    public CaseStatus getToStatus() {
        return toStatus;
    }

    public void setToStatus(CaseStatus toStatus) {
        this.toStatus = toStatus;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public OffsetDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(OffsetDateTime changedAt) {
        this.changedAt = changedAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
