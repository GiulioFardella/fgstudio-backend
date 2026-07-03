package fgstudio_backend.controller;

import fgstudio_backend.dto.ApiResponse;
import fgstudio_backend.dto.ContactRequest;
import fgstudio_backend.service.ContactService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> submit(
            @Valid @RequestBody ContactRequest request,
            HttpServletRequest servletRequest
    ) {
        String ipAddress = servletRequest.getRemoteAddr();
        String userAgent = servletRequest.getHeader("User-Agent");

        contactService.submit(request, ipAddress, userAgent);

        return ResponseEntity.ok(
                ApiResponse.success("Richiesta inviata correttamente.")
        );
    }
}