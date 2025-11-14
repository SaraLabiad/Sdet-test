package com.test.sdet.controller;

import com.test.sdet.domain.Deal;
import com.test.sdet.web.dto.DealRequest;
import com.test.sdet.repository.DealRepository;
import com.test.sdet.service.DealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/deals")
@CrossOrigin(origins = "*")
public class DealController {

    private final DealService service;
    private final DealRepository repo;

    public DealController(DealService service, DealRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> testEndpoint() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK");
        response.put("message", "Deal API is working!");
        response.put("timestamp", OffsetDateTime.now().toString());
        response.put("endpoints", Map.of(
                "GET /api/deals", "Liste tous les deals",
                "GET /api/deals/{id}", "Récupère un deal par ID",
                "POST /api/deals", "Importe une liste de deals",
                "GET /api/deals/test", "Test de l'API"
        ));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Deal>> getAllDeals() {
        List<Deal> deals = repo.findAll();
        return ResponseEntity.ok(deals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deal> getDealById(@PathVariable String id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> importDeals(@RequestBody @Valid List<DealRequest> requests) {
        try {
            List<Deal> deals = requests.stream()
                    .map(this::toEntity)
                    .collect(Collectors.toList());
            List<Deal> saved = service.importDeals(deals);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Deals imported successfully");
            response.put("count", saved.size());
            response.put("deals", saved);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error importing deals: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteDeal(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        if (repo.existsById(id)) {
            repo.deleteById(id);
            response.put("success", true);
            response.put("message", "Deal deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Deal not found with id: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> countDeals() {
        long count = repo.count();
        Map<String, Object> response = new HashMap<>();
        response.put("totalDeals", count);
        return ResponseEntity.ok(response);
    }

    private Deal toEntity(DealRequest r) {
        Deal d = new Deal();
        d.setDealUniqueId(r.getDealUniqueId());
        d.setFromCurrencyIsoCode(r.getFromCurrencyIsoCode());
        d.setToCurrencyIsoCode(r.getToCurrencyIsoCode());
        try {
            d.setDealTimestamp(OffsetDateTime.parse(r.getDealTimestamp()));
        } catch (DateTimeParseException e) {
            d.setDealTimestamp(OffsetDateTime.now());
        }
        d.setDealAmount(r.getDealAmount());
        return d;
    }
}
