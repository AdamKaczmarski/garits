package com.garits.order;

public class PartOrder {
    private Integer partId;
    private int quantity;

    public PartOrder() {
    }

    public PartOrder(Integer partId, int quantity) {
        this.partId = partId;
        this.quantity = quantity;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
