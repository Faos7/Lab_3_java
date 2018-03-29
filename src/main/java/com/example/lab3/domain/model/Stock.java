package com.example.lab3.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stock")
public class Stock implements Serializable {

    @Id
    @SequenceGenerator(name="stock_sequence",sequenceName="stock_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="stock_sequence")
    @Column(name="id", unique=true, nullable=false)
    private Integer stockId;

    @Column(name = "number")
    private Integer number;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_city")
    private City city;

    @Column(name = "adres")
    private String adres;

    @Column(name = "phone")
    private String phone;

    @Column(name = "site")
    private String site;

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Stock() {
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", number=" + number +
                ", city=" + city +
                ", adres='" + adres + '\'' +
                ", phone='" + phone + '\'' +
                ", site='" + site + '\'' +
                '}';
    }

    public String getInfo() {
        return city.getName() + ", stock#" + number + ". " + adres;
    }
}
