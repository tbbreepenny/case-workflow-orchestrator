package com.breanna.workflow.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class CaseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String externalCaseId;   // e.g. COURT-2025-00001
    private String sourceSystem;     // "CountyCourt", "FDLE API", "Node Validator"
    private String defendantName;
    private String county;

    @Enumerated(EnumType.STRING)
    private CaseStatus status;

    private OffsetDateTime createdAt;
    private OffsetDateTime lastUpdated;

    public CaseRecord() {}

    public CaseRecord(String externalCaseId,
                      String sourceSystem,
                      String defendantName,
                      String county,
                      CaseStatus status) {

        this.externalCaseId = externalCaseId;
        this.sourceSystem = sourceSystem;
        this.defendantName = defendantName;
        this.county = county;
        this.status = status;

        this.createdAt = OffsetDateTime.now();
        this.lastUpdated = OffsetDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.lastUpdated = OffsetDateTime.now();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExternalCaseId() {
        return externalCaseId;
    }

    public void setExternalCaseId(String externalCaseId) {
        this.externalCaseId = externalCaseId;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getDefendantName() {
        return defendantName;
    }

    public void setDefendantName(String defendantName) {
        this.defendantName = defendantName;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public CaseStatus getStatus() {
        return status;
    }

    public void setStatus(CaseStatus status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
