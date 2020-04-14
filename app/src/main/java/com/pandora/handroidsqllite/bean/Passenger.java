package com.pandora.handroidsqllite.bean;


public class Passenger {

    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 电话号码
     */
    private String phoneNumber;

    public Passenger() {

    }

    public Passenger(String serialNumber, String name, String nickName, String birthday, String phoneNumber) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.nickName = nickName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
