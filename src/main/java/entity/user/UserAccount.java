package entity.user;

import java.io.Serializable;

public class UserAccount implements Serializable {
    private User userObject;
    private String id;
    private String pw;

    public UserAccount(User userObject, String id, String pw) {
        this.userObject = userObject;
        this.id = id;
        this.pw = pw;
    }

    public static UserAccount CreateObject(User userObject, String id, String pw) {
        return new UserAccount(
                userObject,
                id,
                pw
        );
    }

    public User getUserObject() {
        return userObject;
    }

    public void setUserObject(User userObject) {
        this.userObject = userObject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    @Override
    public String toString() {
        return "User Data\n" + this.userObject.toString() + "\n\n" +
                "User ID : " + this.id + "\n" +
                "User PW : " + this.pw + "\n";
    }
}
