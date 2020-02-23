package com.example.ForestProtect.Base;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Documents")
public class Documents {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name = "id_user")
    private long id_user;

    @Column(name = "id_photo")
    private long id_photo;

    @Column(name = "date")
    private Date date;

    @Column(name = "violation")
    private Boolean violation;

    @Column(name = "content")
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public long getId_photo() {
        return id_photo;
    }

    public void setId_photo(long id_photo) {
        this.id_photo = id_photo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getViolation() {
        return violation;
    }

    public void setViolation(Boolean violation) {
        this.violation = violation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
