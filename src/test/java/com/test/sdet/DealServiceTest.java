package com.test.sdet;

import com.test.sdet.domain.Deal;
import com.test.sdet.repository.DealRepository;
import com.test.sdet.service.DealService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DealServiceTest {

    @Test
    void validate_and_save_success() {
        DealRepository repo = Mockito.mock(DealRepository.class);
        Mockito.when(repo.existsById("D-1")).thenReturn(false);
        DealService s = new DealService(repo);
        Deal d = new Deal();
        d.setDealUniqueId("D-1");
        d.setFromCurrencyIsoCode("USD");
        d.setToCurrencyIsoCode("EUR");
        d.setDealTimestamp(OffsetDateTime.now());
        d.setDealAmount(BigDecimal.valueOf(100));
        assertDoesNotThrow(() -> s.validate(d));
    }

    @Test
    void invalid_amount_throws() {
        DealRepository repo = Mockito.mock(DealRepository.class);
        DealService s = new DealService(repo);
        Deal d = new Deal();
        d.setDealUniqueId("D-2");
        d.setFromCurrencyIsoCode("USD");
        d.setToCurrencyIsoCode("EUR");
        d.setDealTimestamp(OffsetDateTime.now());
        d.setDealAmount(BigDecimal.valueOf(0));
        assertThrows(IllegalArgumentException.class, () -> s.validate(d));
    }
}
