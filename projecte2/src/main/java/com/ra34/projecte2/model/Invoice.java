package com.ra34.projecte2.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity(name = "invoice")
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;

    private LocalDate issueDate;

    private Float taxAmount;

    private Float totalWithTax;

    @OneToOne
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    private Order order;

    public Invoice() {
    }

    public Long getId() {
        return id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public Float getTaxAmount() {
        return taxAmount;
    }

    public Float getTotalWithTax() {
        return totalWithTax;
    }

    public Order getOrder() {
        return order;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public void setTaxAmount(Float taxAmount) {
        this.taxAmount = taxAmount;
    }

    public void setTotalWithTax(Float totalWithTax) {
        this.totalWithTax = totalWithTax;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}