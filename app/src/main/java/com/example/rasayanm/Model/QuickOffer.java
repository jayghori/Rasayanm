package com.example.rasayanm.Model;

import java.io.Serializable;

public class QuickOffer implements Serializable {

    String id;
    String title;
    String stock;
    String discription;
    String locality;
    String countryName;
    String price;
    String imgUrl;
    String stockUnit,priceUnit;
    String gradeUnit;

    QuickOffer(){

    }

    public QuickOffer(String id, String title, String stock, String discription, String locality, String countryName, String price, String imgUrl, String stockUnit, String priceUnit, String gradeUnit) {
        this.id = id;
        this.title = title;
        this.stock = stock;
        this.discription = discription;
        this.locality = locality;
        this.countryName = countryName;
        this.price = price;
        this.imgUrl = imgUrl;
        this.stockUnit = stockUnit;
        this.priceUnit = priceUnit;
        this.gradeUnit = gradeUnit;
   }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getStockUnit() {
        return stockUnit;
    }

    public void setStockUnit(String stockUnit) {
        this.stockUnit = stockUnit;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getGradeUnit() {
        return gradeUnit;
    }

    public void setGradeUnit(String gradeUnit) {
        this.gradeUnit = gradeUnit;
    }
}
