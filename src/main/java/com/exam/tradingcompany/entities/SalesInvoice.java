package com.exam.tradingcompany.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Nguyễn Văn Hà
 * 12:34 AM 5/9/2021
 */
@Entity
@Table(name = "salesinvoice")
public class SalesInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date date;

    @Column
    private String nameSalesStaff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNameSalesStaff() {
        return nameSalesStaff;
    }

    public void setNameSalesStaff(String nameSalesStaff) {
        this.nameSalesStaff = nameSalesStaff;
    }
}
