package fgstudio_backend.repository;

import fgstudio_backend.entity.QuoteRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRequestRepository extends JpaRepository<QuoteRequestEntity, Long> {
}