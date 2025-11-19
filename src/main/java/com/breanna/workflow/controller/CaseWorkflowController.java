package com.breanna.workflow.controller;

import com.breanna.workflow.model.CaseHistory;
import com.breanna.workflow.model.CaseRecord;
import com.breanna.workflow.model.CaseStatus;
import com.breanna.workflow.service.CaseWorkflowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cases")
public class CaseWorkflowController {

    private final CaseWorkflowService service;

    public CaseWorkflowController(CaseWorkflowService service) {
        this.service = service;
    }

    @PostMapping
    public CaseRecord createCase(@RequestBody CreateCaseRequest request) {
        return service.createCase(
                request.getExternalCaseId(),
                request.getSourceSystem(),
                request.getDefendantName(),
                request.getCounty()
        );
    }

    @PostMapping("/{id}/transition")
    public CaseRecord transitionCase(@PathVariable Long id,
                                     @RequestBody TransitionRequest request) {
        return service.transitionCase(
                id,
                request.getNextStatus(),
                request.getActor(),
                request.getNote()
        );
    }

    @GetMapping
    public List<CaseRecord> listCases(@RequestParam(required = false) CaseStatus status,
                                      @RequestParam(required = false) String county) {
        if (status != null) {
            return service.getCasesByStatus(status);
        }
        if (county != null) {
            return service.getCasesByCounty(county);
        }
        return service.getAllCases();
    }

    @GetMapping("/{id}/history")
    public List<CaseHistory> getHistory(@PathVariable Long id) {
        return service.getCaseHistory(id);
    }


    public static class CreateCaseRequest {

        private String externalCaseId;
        private String sourceSystem;
        private String defendantName;
        private String county;

        public CreateCaseRequest() {}

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
    }

    public static class TransitionRequest {

        private CaseStatus nextStatus;
        private String actor;
        private String note;

        public TransitionRequest() {}

        public CaseStatus getNextStatus() {
            return nextStatus;
        }

        public void setNextStatus(CaseStatus nextStatus) {
            this.nextStatus = nextStatus;
        }

        public String getActor() {
            return actor;
        }

        public void setActor(String actor) {
            this.actor = actor;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
}
