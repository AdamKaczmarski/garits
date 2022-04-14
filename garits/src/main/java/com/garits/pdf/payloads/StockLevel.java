package com.garits.pdf.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.sql.Date;

@JsonDeserialize
public class StockLevel {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;

    public StockLevel() {
    }

    public StockLevel(Date dateFrom, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
