package ru.alexanderrogachev.staffer.models;

public class Shop {

    private int shopId;

    private String name;

    private int code;

    public Shop(int shopId, String name, int code) {
        this.shopId = shopId;
        this.name = name;
        this.code = code;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", name='" + name + '\'' +
                ", code=" + code +
                '}';
    }
}
