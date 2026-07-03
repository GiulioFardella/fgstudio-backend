package fgstudio_backend.controller;

import fgstudio_backend.dto.ApiResponse;
import fgstudio_backend.dto.QuoteRequest;
import fgstudio_backend.service.QuoteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quote")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> submit(
            @Valid @RequestBody QuoteRequest request,
            HttpServletRequest servletRequest
    ) {
        String ipAddress = servletRequest.getRemoteAddr();
        String userAgent = servletRequest.getHeader("User-Agent");

        quoteService.submit(request, ipAddress, userAgent);

        return ResponseEntity.ok(
                ApiResponse.success("Richiesta inviata correttamente.")
        );
    }
}