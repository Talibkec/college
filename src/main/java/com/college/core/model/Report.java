package com.college.core.model;

public class Report {
    private Double totalOrderBalance;
    private Double totalOrderPrice;
    private Double totalDiscount;
    private Double totalAdvance;

    public Double getTotalOrderBalance() {
        return totalOrderBalance;
    }

    public void setTotalOrderBalance(Double totalOrderBalance) {
        this.totalOrderBalance = totalOrderBalance;
    }

    public Double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(Double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Double getTotalAdvance() {
        return totalAdvance;
    }

    public void setTotalAdvance(Double totalAdvance) {
        this.totalAdvance = totalAdvance;
    }
}
