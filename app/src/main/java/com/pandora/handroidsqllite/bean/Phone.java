package com.pandora.handroidsqllite.bean;


public class Phone {

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

    public Phone(String brand, String androidId, String imei, String serialNumber, String mac) {
        this.brand = brand;
        this.androidId = androidId;
        this.imei = imei;
        this.serialNumber = serialNumber;
        this.mac = mac;
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
                "brand='" + brand + '\'' +
                ", androidId='" + androidId + '\'' +
                ", imei='" + imei + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }
}
