package fgstudio_backend.dto;

import fgstudio_backend.entity.RequestStatus;

import java.time.LocalDateTime;

public class AdminContactDetail {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String message;
    private RequestStatus status;
    private String adminNote;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AdminContactDetail(
            Long id,
            String name,
            String email,
            String phone,
            String message,
            RequestStatus status,
            String adminNote,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
        this.status = status;
        this.adminNote = adminNote;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getMessage() { return message; }
    public RequestStatus getStatus() { return status; }
    public String getAdminNote() { return adminNote; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}