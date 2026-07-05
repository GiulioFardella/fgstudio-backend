package fgstudio_backend.controller;

import fgstudio_backend.dto.AdminQuoteDetail;
import fgstudio_backend.dto.AdminRequestSummary;
import fgstudio_backend.dto.AdminRequestUpdate;
import fgstudio_backend.service.QuoteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/quotes")
public class AdminQuoteController {

    private final QuoteService quoteService;

    public AdminQuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public List<AdminRequestSummary> getAllQuotes() {
        return quoteService.getAllForAdmin();
    }

    @GetMapping("/{id}")
    public AdminQuoteDetail getQuoteById(@PathVariable Long id) {
        return quoteService.getByIdForAdmin(id);
    }

    @PatchMapping("/{id}")
    public AdminQuoteDetail updateQuote(
            @PathVariable Long id,
            @Valid @RequestBody AdminRequestUpdate request
    ) {
        return quoteService.updateForAdmin(id, request);
    }
}