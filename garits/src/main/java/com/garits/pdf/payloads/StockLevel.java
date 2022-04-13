package com.garits.pdf.payloads;

import java.sql.Date;

public class StockLevel {
    private Date dateFrom;
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
