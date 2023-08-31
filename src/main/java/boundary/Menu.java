package boundary;

import controller.Printer;
import controller.Serializer;
import controller.exceptions.AgeException;
import controller.exceptions.InvalidPasswordException;
import controller.exceptions.NoTypeException;
import controller.exceptions.PhoneNumberException;
import entity.account.*;
import entity.user.User;
import entity.user.UserAccount;

import java.io.IOException;

public class Menu {
    Account selectAccount = null;
    public boolean userIDMake(String name, int age, String PhoneNumber) throws AgeException, PhoneNumberException {
        if(age >= 14)
        {
            throw new AgeException("나이가 14세 이상이어야 합니다.");
        }
        if(PhoneNumber.length() == 11 || PhoneNumber.length() == 10) {
            User user = User.CreateObject(name, age, PhoneNumber);
            return true;
        }
        throw new PhoneNumberException("올바르지 않은 전화번호 입니다.");
    }

    public boolean userAccountMake(User user, String id, String pw)
    {
        UserAccount account = UserAccount.CreateObject(user, id, pw);
        return true;
    }

    public void accountInquiry(String id, String pw, String type) throws IOException, NoTypeException {
        if (type.equals("Account"))
        {
            String fileName = "\\account\\Account"+id+".cbp";
            Serializer<Account> serializer = new Serializer<Account>(Account.class);
            selectAccount = serializer.loadObject(fileName);
        }
        else if (type.equals("Installment"))
        {
            String fileName = "\\account\\Installment"+id+".cbp";
            Serializer<InstallmentAccount> serializer = new Serializer<InstallmentAccount>(InstallmentAccount.class);
            selectAccount = serializer.loadObject(fileName);
        }
        else if (type.equals("Minus"))
        {
            String fileName = "\\account\\Minus"+id+".cbp";
            Serializer<MinusAccount> serializer = new Serializer<MinusAccount>(MinusAccount.class);
            selectAccount = serializer.loadObject(fileName);
        }
        else if (type.equals("Point"))
        {
            String fileName = "\\account\\Point"+id+".cbp";
            Serializer<PointAccount> serializer = new Serializer<PointAccount>(PointAccount.class);
            selectAccount = serializer.loadObject(fileName);
        }
        else if (type.equals("Saving"))
        {
            String fileName = "\\account\\Minus"+id+".cbp";
            Serializer<SavingAccount> serializer = new Serializer<SavingAccount>(SavingAccount.class);
            selectAccount = serializer.loadObject(fileName);
        }
        else {
            throw new NoTypeException("존재하지 않는 형식입니다.");
        }
    }

    public void accountDeposit(long amount, String pw)
    {
        try {
            selectAccount.deposit(amount, pw);
        }
        catch(InvalidPasswordException e)
        {
            Printer.print("비밀번호가 틀렸습니다.");
        }
    }
}
