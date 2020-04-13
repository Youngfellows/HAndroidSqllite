package com.pandora.handroidsqllite.bean;


public class Phone {

    /**
     * ID号
     */
    private long id;

    /**
     * 品牌
     */
    private String brand;

    /**
     * Android ID
     */
    private String androidId;

    /**
     * imei
     */
    private String imei;

    /**
     * 序列号
     */
    private String serialNumber;

    /**
     * MAC地址
     */
    private String mac;

    public Phone() {

    }

    public Phone(long id, String brand, String androidId, String imei, String serialNumber, String mac) {
        this.id = id;
        this.brand = brand;
        this.androidId = androidId;
        this.imei = imei;
        this.serialNumber = serialNumber;
        this.mac = mac;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }


    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", androidId='" + androidId + '\'' +
                ", imei='" + imei + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }
}
