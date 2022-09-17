package ru.alexanderrogachev.staffer.models;

import java.time.Clock;
import java.util.Date;

public class StafferShops {

    private int stafferId;

    private int shopId;

    private String shopName;

    private Date dateOfRequest;

    private Date dateOfWork;

    private Clock startTime;

    private Clock endTime;

    private boolean confirmation;

    public StafferShops(int stafferId, int shopId, String shopName, Date dateOfRequest, Date dateOfWork, Clock startTime, Clock endTime, boolean confirmation) {
        this.stafferId = stafferId;
        this.shopId = shopId;
        this.shopName = shopName;
        this.dateOfRequest = dateOfRequest;
        this.dateOfWork = dateOfWork;
        this.startTime = startTime;
        this.endTime = endTime;
        this.confirmation = confirmation;
    }

    public int getStafferId() {
        return stafferId;
    }

    public void setStafferId(int stafferId) {
        this.stafferId = stafferId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Date getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(Date dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    public Date getDateOfWork() {
        return dateOfWork;
    }

    public void setDateOfWork(Date dateOfWork) {
        this.dateOfWork = dateOfWork;
    }

    public Clock getStartTime() {
        return startTime;
    }

    public void setStartTime(Clock startTime) {
        this.startTime = startTime;
    }

    public Clock getEndTime() {
        return endTime;
    }

    public void setEndTime(Clock endTime) {
        this.endTime = endTime;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    @Override
    public String toString() {
        return "StafferShops{" +
                "stafferId=" + stafferId +
                ", shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", dateOfRequest=" + dateOfRequest +
                ", dateOfWork=" + dateOfWork +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", confirmation=" + confirmation +
                '}';
    }
}
