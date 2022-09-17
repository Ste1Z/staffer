package ru.alexanderrogachev.staffer.models;

import java.time.Clock;
import java.util.Date;

public class ShopStaffers {

    private int shopId;

    private int stafferId;

    private String stafferName;

    private String stafferSurname;

    private String stafferPatronymic;

    private Date stafferDateOfBirth;

    private Date dateOfRequest;

    private Date dateOfWork;

    private Clock startTime;

    private Clock endTime;

    private boolean confirmation;

    public ShopStaffers(int shopId, int stafferId, String stafferName, String stafferSurname, String stafferPatronymic,
                        Date stafferDateOfBirth, Date dateOfRequest, Date dateOfWork, Clock startTime, Clock endTime, boolean confirmation) {
        this.shopId = shopId;
        this.stafferId = stafferId;
        this.stafferName = stafferName;
        this.stafferSurname = stafferSurname;
        this.stafferPatronymic = stafferPatronymic;
        this.stafferDateOfBirth = stafferDateOfBirth;
        this.dateOfRequest = dateOfRequest;
        this.dateOfWork = dateOfWork;
        this.startTime = startTime;
        this.endTime = endTime;
        this.confirmation = confirmation;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getStafferId() {
        return stafferId;
    }

    public void setStafferId(int stafferId) {
        this.stafferId = stafferId;
    }

    public String getStafferName() {
        return stafferName;
    }

    public void setStafferName(String stafferName) {
        this.stafferName = stafferName;
    }

    public String getStafferSurname() {
        return stafferSurname;
    }

    public void setStafferSurname(String stafferSurname) {
        this.stafferSurname = stafferSurname;
    }

    public String getStafferPatronymic() {
        return stafferPatronymic;
    }

    public void setStafferPatronymic(String stafferPatronymic) {
        this.stafferPatronymic = stafferPatronymic;
    }

    public Date getStafferDateOfBirth() {
        return stafferDateOfBirth;
    }

    public void setStafferDateOfBirth(Date stafferDateOfBirth) {
        this.stafferDateOfBirth = stafferDateOfBirth;
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
        return "ShopStaffers{" +
                "shopId=" + shopId +
                ", stafferId=" + stafferId +
                ", stafferName='" + stafferName + '\'' +
                ", stafferSurname='" + stafferSurname + '\'' +
                ", stafferPatronymic='" + stafferPatronymic + '\'' +
                ", stafferDateOfBirth=" + stafferDateOfBirth +
                ", dateOfRequest=" + dateOfRequest +
                ", dateOfWork=" + dateOfWork +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", confirmation=" + confirmation +
                '}';
    }
}
