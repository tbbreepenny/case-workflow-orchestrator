# Case Workflow Orchestrator (Java / Spring Boot)

A Java Spring Boot service that manages the **lifecycle of justice-system cases** using a simple workflow engine.  
Cases move through statuses like `INTAKE → PENDING_REVIEW → UNDER_INVESTIGATION → CLOSED`, with every transition logged in an audit history.

This project complements:

- **Court → FDLE Pipeline (.NET + Python)**
- **Court Record Validator API (Node.js)**

by acting as the **workflow brain** that tracks state and history after data is taken and validated.

---

## Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Web (REST APIs)**
- **Spring Data JPA**
- **H2 in-memory database**
- **Maven**

---

## 📁 Project Structure

```text
case-workflow-orchestrator/
│
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/breanna/workflow/
│   │   │       ├── CaseWorkflowOrchestratorApplication.java
│   │   │       ├── model/
│   │   │       │   ├── CaseRecord.java
│   │   │       │   ├── CaseHistory.java
│   │   │       │   └── CaseStatus.java
│   │   │       ├── repository/
│   │   │       │   ├── CaseRecordRepository.java
│   │   │       │   └── CaseHistoryRepository.java
│   │   │       ├── service/
│   │   │       │   └── CaseWorkflowService.java
│   │   │       └── controller/
│   │   │           └── CaseWorkflowController.java
│   │   └── resources/
│   │       └── application.properties
│      
