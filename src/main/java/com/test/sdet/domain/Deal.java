package com.test.sdet.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "deals")
public class Deal {

    @Id
    private String dealUniqueId;
    private String fromCurrencyIsoCode;
    private String toCurrencyIsoCode;
    private OffsetDateTime dealTimestamp;
    private BigDecimal dealAmount;

    public Deal() {}

    // getters & setters
    public String getDealUniqueId() { return dealUniqueId; }
    public void setDealUniqueId(String dealUniqueId) { this.dealUniqueId = dealUniqueId; }
    public String getFromCurrencyIsoCode() { return fromCurrencyIsoCode; }
    public void setFromCurrencyIsoCode(String fromCurrencyIsoCode) { this.fromCurrencyIsoCode = fromCurrencyIsoCode; }
    public String getToCurrencyIsoCode() { return toCurrencyIsoCode; }
    public void setToCurrencyIsoCode(String toCurrencyIsoCode) { this.toCurrencyIsoCode = toCurrencyIsoCode; }
    public OffsetDateTime getDealTimestamp() { return dealTimestamp; }
    public void setDealTimestamp(OffsetDateTime dealTimestamp) { this.dealTimestamp = dealTimestamp; }
    public BigDecimal getDealAmount() { return dealAmount; }
    public void setDealAmount(BigDecimal dealAmount) { this.dealAmount = dealAmount; }
}
