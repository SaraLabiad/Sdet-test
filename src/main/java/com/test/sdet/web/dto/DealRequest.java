package com.test.sdet.web.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class DealRequest {

    @NotBlank
    private String dealUniqueId;

    @NotBlank
    @Size(min = 3, max = 3)
    private String fromCurrencyIsoCode;

    @NotBlank
    @Size(min = 3, max = 3)
    private String toCurrencyIsoCode;

    @NotBlank
    private String dealTimestamp; // ISO-8601 string

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal dealAmount;

    // getters/setters
    public String getDealUniqueId() { return dealUniqueId; }
    public void setDealUniqueId(String dealUniqueId) { this.dealUniqueId = dealUniqueId; }
    public String getFromCurrencyIsoCode() { return fromCurrencyIsoCode; }
    public void setFromCurrencyIsoCode(String fromCurrencyIsoCode) { this.fromCurrencyIsoCode = fromCurrencyIsoCode; }
    public String getToCurrencyIsoCode() { return toCurrencyIsoCode; }
    public void setToCurrencyIsoCode(String toCurrencyIsoCode) { this.toCurrencyIsoCode = toCurrencyIsoCode; }
    public String getDealTimestamp() { return dealTimestamp; }
    public void setDealTimestamp(String dealTimestamp) { this.dealTimestamp = dealTimestamp; }
    public BigDecimal getDealAmount() { return dealAmount; }
    public void setDealAmount(BigDecimal dealAmount) { this.dealAmount = dealAmount; }
}
