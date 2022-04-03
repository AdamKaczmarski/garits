package com.garits.order;

import com.garits.part.Part;

import javax.persistence.*;

@Entity
@Table(schema = "parts_orders")
public class PartsOrdersDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_parts_order")
    private Integer idPartsOrder;
    @Column(name="quantity_ordered")
    private int quantityOrdered;
    @Column(name="order_id")
    private Integer orderId;
    @OneToOne
    @JoinColumn(name="part_id", referencedColumnName = "id_part")
    private Part part;

    public Integer getIdPartsOrder() {
        return idPartsOrder;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}

