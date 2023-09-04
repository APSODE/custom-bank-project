package entity.user;

import controller.Judger;
import controller.exceptions.InvalidPhoneNumberFormat;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private int age;
    private String phoneNumber;

    public User(String name, int age, String phoneNumber) throws InvalidPhoneNumberFormat{
        this.name = name;
        this.age = age;
        if (!Judger.isValidPhoneNumber(phoneNumber)) {
            throw new InvalidPhoneNumberFormat("사용가능한 전화번호 형식이 아닙니다.");
        }
        this.phoneNumber = phoneNumber;
    }

    public static User CreateObject(String name, int age, String phoneNumber) throws InvalidPhoneNumberFormat{
        return new User(
                name,
                age,
                phoneNumber
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberFormat{
        if (!Judger.isValidPhoneNumber(phoneNumber)) {
            throw new InvalidPhoneNumberFormat("사용가능한 전화번호 형식이 아닙니다.");
        }

        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User Name : " + this.name + "\n" +
                "User Age : " + this.age + "\n" +
                "user Phone Number : " + this.phoneNumber + "\n";
    }
}
