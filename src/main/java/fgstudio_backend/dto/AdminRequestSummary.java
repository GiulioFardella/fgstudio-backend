package fgstudio_backend.dto;

import fgstudio_backend.entity.RequestStatus;

import java.time.LocalDateTime;

public class AdminRequestSummary {

    private Long id;
    private String name;
    private String email;
    private RequestStatus status;
    private LocalDateTime createdAt;

    public AdminRequestSummary(
            Long id,
            String name,
            String email,
            RequestStatus status,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}