package com.project.ShauntoshThapa.fernweh.models;

import java.io.Serializable;

/**
 * Created by Shauntosh Thapa on 10/1/2016.
 */

public class Trip implements Serializable {

    private String destination;
    private String interest;
    private String rafting;
    private String bungee;
    private String canyoing;
    private String hiking;
    private String paragliding;
    private String cycling;
    private String duration;
    private String kids;
    private String adults;
    private String budget;
    private  static  final long serialVersionUID=92893829892L;


    public Trip() {
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAdults() {
        return adults;
    }

    public void setAdults(String adults) {
        this.adults = adults;
    }

    public String getKids() {
        return kids;
    }

    public void setKids(String kids) {
        this.kids = kids;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCycling() {
        return cycling;
    }

    public void setCycling(String cycling) {
        this.cycling = cycling;
    }

    public String getParagliding() {
        return paragliding;
    }

    public void setParagliding(String paragliding) {
        this.paragliding = paragliding;
    }

    public String getHiking() {
        return hiking;
    }

    public void setHiking(String hiking) {
        this.hiking = hiking;
    }

    public String getCanyoing() {
        return canyoing;
    }

    public void setCanyoing(String canyoing) {
        this.canyoing = canyoing;
    }

    public String getRafting() {
        return rafting;
    }

    public void setRafting(String rafting) {
        this.rafting = rafting;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getBungee() {
        return bungee;
    }

    public void setBungee(String bungee) {
        this.bungee = bungee;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }
}
