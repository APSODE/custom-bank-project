package entity.user;

public class User {
    private String name;
    private int age;

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

}
