package entity.user;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private int age;
    private String phoneNumber;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static User CreateObject(String name, int age) {
        return new User(
                name,
                age
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User Name : " + this.name + "\n" +
                "User Age : " + this.age + "\n" +
                "user Phone Number : " + this.phoneNumber + "\n";
    }
}
