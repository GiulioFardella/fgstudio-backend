package fgstudio_backend.controller;

import fgstudio_backend.dto.AdminContactDetail;
import fgstudio_backend.dto.AdminRequestSummary;
import fgstudio_backend.service.ContactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/contacts")
public class AdminContactController {

    private final ContactService contactService;

    public AdminContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<AdminRequestSummary> getAllContacts() {
        return contactService.getAllForAdmin();
    }

    @GetMapping("/{id}")
    public AdminContactDetail getContactById(@PathVariable Long id) {
        return contactService.getByIdForAdmin(id);
    }
}