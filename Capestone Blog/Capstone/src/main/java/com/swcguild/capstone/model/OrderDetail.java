/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstone.model;

import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class OrderDetail {

    private int orderId;
    private int productId;
    private int quantity;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.orderId;
        hash = 67 * hash + this.productId;
        hash = 67 * hash + this.quantity;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderDetail other = (OrderDetail) obj;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.productId != other.productId) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        return true;
    }

}
