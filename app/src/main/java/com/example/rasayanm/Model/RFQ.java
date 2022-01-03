package com.example.rasayanm.Model;

public class RFQ {


    String productName;
    String  quantity;
    String latitude;
    String longitude;
    String  gradeUnit;
    String quantityUnit;
    String rfqId;
    String uploadBy;
    String discription;


    public  RFQ(){

    }

    public RFQ(String productName, String quantity, String latitude, String longitude, String gradeUnit, String quantityUnit, String rfqId, String uploadBy, String discription) {
        this.productName = productName;
        this.quantity = quantity;
        this.latitude = latitude;
        this.longitude = longitude;
        this.gradeUnit = gradeUnit;
        this.quantityUnit = quantityUnit;
        this.rfqId = rfqId;
        this.uploadBy = uploadBy;
        this.discription = discription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getGradeUnit() {
        return gradeUnit;
    }

    public void setGradeUnit(String gradeUnit) {
        this.gradeUnit = gradeUnit;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public String getRfqId() {
        return rfqId;
    }

    public void setRfqId(String rfqId) {
        this.rfqId = rfqId;
    }

    public String getUploadBy() {
        return uploadBy;
    }

    public void setUploadBy(String uploadBy) {
        this.uploadBy = uploadBy;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
