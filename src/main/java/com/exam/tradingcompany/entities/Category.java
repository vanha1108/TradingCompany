package com.exam.tradingcompany.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Nguyễn Văn Hà
 * 12:40 AM 5/9/2021
 */
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> news = new ArrayList<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public List<Product> getNews() {
        return news;
    }

    public void setNews(List<Product> news) {
        this.news = news;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
