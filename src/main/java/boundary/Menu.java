package boundary;

import entity.user.User;

public class Menu {
    public boolean userIDMake(String name, int age, String PhoneNumber)
    {
        if(age == 0 || PhoneNumber == null)
        {
            return false;
        }
        User user = User.CreateObject(name, age, PhoneNumber);
        return true;
    }
}
