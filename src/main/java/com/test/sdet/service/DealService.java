package com.test.sdet.service;
import com.test.sdet.domain.Deal;
import com.test.sdet.repository.DealRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DealService {
    private final DealRepository repository;
    private final Logger log = LoggerFactory.getLogger(DealService.class);

    public DealService(DealRepository repository) {
        this.repository = repository;
    }

    public List<Deal> importDeals(List<Deal> deals) {
        List<Deal> saved = new ArrayList<>();
        for (Deal d : deals) {
            try {
                validate(d);
                if (repository.existsById(d.getDealUniqueId())) {
                    log.warn("Duplicate deal id skipped: {}", d.getDealUniqueId());
                    continue;
                }
                saved.add(repository.save(d));
            } catch (Exception e) {
                log.error("Failed to import deal {}: {}", d.getDealUniqueId(), e.getMessage());
            }
        }
        return saved;
    }

    public void validate(Deal d) {
        if (d.getDealUniqueId() == null || d.getDealUniqueId().isBlank()) throw new IllegalArgumentException("dealUniqueId required");
        if (d.getDealAmount() == null) throw new IllegalArgumentException("dealAmount required");
        if (d.getDealAmount().doubleValue() <= 0) throw new IllegalArgumentException("dealAmount must be > 0");
        if (d.getFromCurrencyIsoCode() == null || d.getFromCurrencyIsoCode().length() != 3) throw new IllegalArgumentException("fromCurrencyIsoCode invalid");
        if (d.getToCurrencyIsoCode() == null || d.getToCurrencyIsoCode().length() != 3) throw new IllegalArgumentException("toCurrencyIsoCode invalid");
        if (d.getDealTimestamp() == null) throw new IllegalArgumentException("dealTimestamp required");

    }
}