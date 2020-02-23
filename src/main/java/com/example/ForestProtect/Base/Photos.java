package com.example.ForestProtect.Base;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Photos")
public class Photos {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name = "File")
    private String File;

    @Column(name = "name")
    private String name;

    @Column(name = "id_user")
    private long id_user;

    @Column(name = "date")
    private Date date;

    @Column(name = "coordinates")
    private String coordinates;

    @Column(name = "verification")
    private Boolean verification;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFile() {
        return File;
    }

    public void setFile(String file) {
        File = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public Boolean getVerification() {
        return verification;
    }

    public void setVerification(Boolean verification) {
        this.verification = verification;
    }
}
