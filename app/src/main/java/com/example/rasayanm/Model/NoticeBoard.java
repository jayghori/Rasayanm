package com.example.rasayanm.Model;

public class NoticeBoard {
    String id;
    String title;
    String discription;
    String link;
    String imgUrl;
    String categeryUnit;

    public NoticeBoard(){

    }

    public NoticeBoard(String id, String title, String discription, String link, String imgUrl, String categeryUnit) {
        this.id = id;
        this.title = title;
        this.discription = discription;
        this.link = link;
        this.imgUrl = imgUrl;
        this.categeryUnit = categeryUnit;
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCategeryUnit() {
        return categeryUnit;
    }

    public void setCategeryUnit(String categeryUnit) {
        this.categeryUnit = categeryUnit;
    }
}
