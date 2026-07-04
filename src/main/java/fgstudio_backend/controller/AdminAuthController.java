package fgstudio_backend.controller;

import fgstudio_backend.dto.AdminLoginRequest;
import fgstudio_backend.dto.ApiResponse;
import fgstudio_backend.service.AdminAuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/auth")
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    private final SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    public AdminAuthController(AdminAuthService adminAuthService) {
        this.adminAuthService = adminAuthService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(
            @Valid @RequestBody AdminLoginRequest request,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse
    ) {
        Authentication authentication = adminAuthService.authenticate(request);

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);

        securityContextRepository.saveContext(
                securityContext,
                servletRequest,
                servletResponse
        );

        return ResponseEntity.ok(
                ApiResponse.success("Accesso effettuato correttamente.")
        );
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse> me() {
        return ResponseEntity.ok(
                ApiResponse.success("Sessione admin attiva.")
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse
    ) {
        SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
        SecurityContextHolder.setContext(emptyContext);

        securityContextRepository.saveContext(
                emptyContext,
                servletRequest,
                servletResponse
        );

        if (servletRequest.getSession(false) != null) {
            servletRequest.getSession(false).invalidate();
        }

        return ResponseEntity.ok(
                ApiResponse.success("Logout effettuato.")
        );
    }
}