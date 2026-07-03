package fgstudio_backend.service;

import fgstudio_backend.dto.QuoteRequest;
import fgstudio_backend.entity.QuoteRequestEntity;
import fgstudio_backend.repository.QuoteRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuoteService {

    private static final Logger log = LoggerFactory.getLogger(QuoteService.class);

    private final QuoteRequestRepository quoteRequestRepository;

    public QuoteService(QuoteRequestRepository quoteRequestRepository) {
        this.quoteRequestRepository = quoteRequestRepository;
    }

    @Transactional
    public void submit(QuoteRequest request, String ipAddress, String userAgent) {
        if (isHoneypotFilled(request.getWebsite())) {
            log.info("Quote request blocked by honeypot.");
            return;
        }

        QuoteRequestEntity quote = new QuoteRequestEntity();

        quote.setName(clean(request.getName()));
        quote.setEmail(clean(request.getEmail()).toLowerCase());
        quote.setPhone(cleanOptional(request.getPhone()));
        quote.setCompany(clean(request.getCompany()));
        quote.setSector(clean(request.getSector()));
        quote.setSiteType(clean(request.getSiteType()));
        quote.setGoal(clean(request.getGoal()));
        quote.setExistingSite(cleanOptional(request.getExistingSite()));
        quote.setBudget(clean(request.getBudget()));
        quote.setTimeline(clean(request.getTimeline()));
        quote.setMessage(clean(request.getMessage()));
        quote.setConsent(request.getConsent());
        quote.setIpAddress(truncate(ipAddress, 64));
        quote.setUserAgent(truncate(userAgent, 500));

        quoteRequestRepository.save(quote);

        log.info("New quote request saved.");
    }

    private boolean isHoneypotFilled(String website) {
        return website != null && !website.isBlank();
    }

    private String clean(String value) {
        return value.trim();
    }

    private String cleanOptional(String value) {
        return value == null || value.isBlank() ? null : value.trim();
    }

    private String truncate(String value, int maxLength) {
        if (value == null || value.isBlank()) {
            return null;
        }

        return value.length() <= maxLength
                ? value
                : value.substring(0, maxLength);
    }
}