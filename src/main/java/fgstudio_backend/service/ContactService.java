package fgstudio_backend.service;

import fgstudio_backend.dto.AdminContactDetail;
import fgstudio_backend.dto.AdminRequestSummary;
import fgstudio_backend.dto.AdminRequestUpdate;
import fgstudio_backend.dto.ContactRequest;
import fgstudio_backend.entity.ContactMessage;
import fgstudio_backend.exception.ResourceNotFoundException;
import fgstudio_backend.repository.ContactMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactService {

    private static final Logger log =
            LoggerFactory.getLogger(ContactService.class);

    private final ContactMessageRepository contactMessageRepository;

    public ContactService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    @Transactional
    public void submit(ContactRequest request, String ipAddress, String userAgent) {
        if (isHoneypotFilled(request.getWebsite())) {
            log.info("Contact request blocked by honeypot.");
            return;
        }

        ContactMessage message = new ContactMessage();
        message.setName(clean(request.getName()));
        message.setEmail(clean(request.getEmail()).toLowerCase());
        message.setPhone(cleanOptional(request.getPhone()));
        message.setMessage(clean(request.getMessage()));
        message.setIpAddress(truncate(ipAddress, 64));
        message.setUserAgent(truncate(userAgent, 500));

        contactMessageRepository.save(message);

        log.info("New contact request saved.");
    }

    @Transactional(readOnly = true)
    public List<AdminRequestSummary> getAllForAdmin() {
        return contactMessageRepository
                .findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(this::toAdminSummary)
                .toList();
    }

    @Transactional(readOnly = true)
    public AdminContactDetail getByIdForAdmin(Long id) {
        ContactMessage contact = contactMessageRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Richiesta non trovata.")
                );

        return toAdminDetail(contact);
    }

    @Transactional
    public AdminContactDetail updateForAdmin(
            Long id,
            AdminRequestUpdate request
    ) {
        ContactMessage contact = contactMessageRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Richiesta non trovata.")
                );

        contact.setStatus(request.getStatus());
        contact.setAdminNote(cleanOptional(request.getAdminNote()));

        ContactMessage savedContact =
                contactMessageRepository.saveAndFlush(contact);

        return toAdminDetail(savedContact);
    }

    private AdminRequestSummary toAdminSummary(ContactMessage contact) {
        return new AdminRequestSummary(
                contact.getId(),
                contact.getName(),
                contact.getEmail(),
                contact.getStatus(),
                contact.getCreatedAt()
        );
    }

    private AdminContactDetail toAdminDetail(ContactMessage contact) {
        return new AdminContactDetail(
                contact.getId(),
                contact.getName(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getMessage(),
                contact.getStatus(),
                contact.getAdminNote(),
                contact.getCreatedAt(),
                contact.getUpdatedAt()
        );
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