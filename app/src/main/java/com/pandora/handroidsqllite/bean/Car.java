package com.pandora.handroidsqllite.bean;


public class Car {

    /**
     * ID号
     */
    private long id;

    /**
     * 车辆编号
     */
    private String carNumber;


    /**
     * 车架号
     */
    private String vin;

    /**
     * 车牌号
     */
    private String plateNumber;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 车颜色
     */
    private String colour;

    public Car() {

    }

    public Car(long id, String carNumber, String vin, String plateNumber, String brand, String colour) {
        this.id = id;
        this.carNumber = carNumber;
        this.vin = vin;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.colour = colour;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carNumber='" + carNumber + '\'' +
                ", vin='" + vin + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
