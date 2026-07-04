package fgstudio_backend.dto;

import fgstudio_backend.entity.RequestStatus;

import java.time.LocalDateTime;

public class AdminQuoteDetail {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String company;
    private String sector;
    private String siteType;
    private String goal;
    private String existingSite;
    private String budget;
    private String timeline;
    private String message;
    private Boolean consent;
    private RequestStatus status;
    private String adminNote;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AdminQuoteDetail(
            Long id,
            String name,
            String email,
            String phone,
            String company,
            String sector,
            String siteType,
            String goal,
            String existingSite,
            String budget,
            String timeline,
            String message,
            Boolean consent,
            RequestStatus status,
            String adminNote,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.company = company;
        this.sector = sector;
        this.siteType = siteType;
        this.goal = goal;
        this.existingSite = existingSite;
        this.budget = budget;
        this.timeline = timeline;
        this.message = message;
        this.consent = consent;
        this.status = status;
        this.adminNote = adminNote;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getCompany() { return company; }
    public String getSector() { return sector; }
    public String getSiteType() { return siteType; }
    public String getGoal() { return goal; }
    public String getExistingSite() { return existingSite; }
    public String getBudget() { return budget; }
    public String getTimeline() { return timeline; }
    public String getMessage() { return message; }
    public Boolean getConsent() { return consent; }
    public RequestStatus getStatus() { return status; }
    public String getAdminNote() { return adminNote; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}