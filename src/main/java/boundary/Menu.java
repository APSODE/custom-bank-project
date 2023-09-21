package boundary;

import controller.Printer;
import controller.Serializer;
import controller.Judger;
import entity.account.*;
import entity.user.User;
import entity.user.UserAccount;
import exceptions.*;

import java.io.IOException;

public class Menu {
    private Account selectAccount = null;
    public boolean userIDMake(String name, int age, String PhoneNumber) throws AgeException, PhoneNumberException, InvalidPhoneNumberFormat {
        if(age >= 14)
        {
            throw new AgeException("나이가 14세 이상이어야 합니다.");
        }
        if(Judger.isValidPhoneNumber(PhoneNumber)) {
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
        final String FILE_PATH = "\\account"; final String FILE_EXTENSION = ".cbp";
        String filename = FILE_PATH + type + id + FILE_EXTENSION;
        Serializer serializer = switch (type) {
            case "Account" -> new Serializer<>(Account.class);
            case "Installment" -> new Serializer<>(InstallmentAccount.class);
            case "Minus" -> new Serializer<>(MinusAccount.class);
            case "Point" -> new Serializer<>(PointAccount.class);
            case "Saving" -> new Serializer<>(SavingAccount.class);
            default -> throw new NoTypeException("존재하지 않는 형식입니다.");
        };
        this.selectAccount = (Account) serializer.loadObject(filename);
    }

    // 테스트 코드 동작용으로 임시로 throws작성
    public void accountDeposit(long amount, String pw) throws NegativeAmountException, InvalidPasswordException, ZeroAmountException {
        try {
            this.selectAccount.deposit(amount, pw);
        }
        catch(InvalidPasswordException e)
        {
            Printer.print(e.getMessage());
        }
    }
    public void accountWithdraw(long amount, String pw) throws NegativeAmountException, InvalidPasswordException, ZeroAmountException {
        try {
            this.selectAccount.withdraw(amount, pw);
        }
        catch (IllegalArgumentException | BalanceException e)
        {
            Printer.print(e.getMessage());
        }
    }

}
