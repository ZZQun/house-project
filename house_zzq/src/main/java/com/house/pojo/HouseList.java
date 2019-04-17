package com.house.pojo;

public class HouseList {

    private Integer houseId;
    private String address;
    private Double price;
    private String status;
    private String detail;
    private Integer userlist_Id;
    private String userlist_Name;

    public Integer getUserlist_Id() {
        return userlist_Id;
    }

    public void setUserlist_Id(Integer userlist_Id) {
        this.userlist_Id = userlist_Id;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUserlist_Name() {
        return userlist_Name;
    }

    public void setUserlist_Name(String userlist_Name) {
        this.userlist_Name = userlist_Name;
    }
}
