package com.pandora.handroidsqllite.bean;


public class Driver {

    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 驾照号
     */
    private String drivingLicence;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 联络地址
     */
    private String contactAddress;

    /**
     * 户籍地址
     */
    private String address;

    public Driver() {

    }

    public Driver(String serialNumber, String name, String idCard, String birthday, String drivingLicence, String phoneNumber, String contactAddress, String address) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.idCard = idCard;
        this.birthday = birthday;
        this.drivingLicence = drivingLicence;
        this.phoneNumber = phoneNumber;
        this.contactAddress = contactAddress;
        this.address = address;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDrivingLicence() {
        return drivingLicence;
    }

    public void setDrivingLicence(String drivingLicence) {
        this.drivingLicence = drivingLicence;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", birthday='" + birthday + '\'' +
                ", drivingLicence='" + drivingLicence + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", contactAddress='" + contactAddress + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
