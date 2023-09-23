package boundary;

import entity.account.*;
import entity.user.UserAccount;
import exceptions.*;
import controller.Serializer;
import controller.Printer;
import controller.Inputter;
import java.io.*;
import java.util.TooManyListenersException;

public class Main {
    static Menu menu = new Menu();
    public static void main(String[] args) throws IOException
    {
        String select = "";
        String[] selectAccount;
        LOOP: while (true)
        {
            mainMenuPrint();
            try {
                select = Inputter.inpString();
                switch (select) {
                    case "1":
                        Printer.print("계좌를 선택해 주세요.(id pw 종류) \n (예 : abc bcd 포인트) : ");
                        selectAccount = Inputter.inpString().split(" ");

                        if (selectAccount.length != 3)
                        {
                            Printer.print("잘못된 입력입니다.\n");
                            break;
                        }

                        try {
                            menu.accountInquiry(selectAccount[0], converter(selectAccount[2]));
                            Printer.print("얼마 만큼 인출하시겠습니까?\n");
                            int amount = Inputter.inpInt();
                            menu.accountWithdraw(amount, selectAccount[1]);
                            Printer.print("계좌에" + amount + "만큼 인출되어 현재 "+menu.getAccountBalance()+"원 있습니다.");
                        }

                        catch (NoTypeException e)
                        {
                            Printer.print("선택하신 계좌가 없습니다.\n");
                            break;
                        }

                        catch (EmptyInputException | TooManyInputException | NumberFormatException |
                               NegativeAmountException | ZeroAmountException e)
                        {
                            Printer.print("잘못된 입력입니다.\n");
                            break;
                        }

                        catch (InvalidPasswordException e)
                        {
                            Printer.print(e.getMessage()+"\n"); break;
                        }

                        break;

                    case "2":
                        Printer.print("계좌를 선택해 주세요.(id pw 종류)\n (예 : abc bcd 포인트) : ");
                        selectAccount = Inputter.inpString().split(" ");

                        if (selectAccount.length != 3)
                        {
                            Printer.print("잘못된 입력입니다.\n");
                            break;
                        }

                        try {
                            menu.accountInquiry(selectAccount[0], converter(selectAccount[2]));
                            Printer.print("현재 "+menu.checkAccountBalance(selectAccount[1])+"원 있습니다.");
                        }

                        catch (NoTypeException e)
                        {
                            Printer.print("선택하신 계좌가 없습니다.\n");
                            break;
                        }

                        catch (InvalidPasswordException e)
                        {
                            Printer.print(e.getMessage()+"\n"); break;
                        }

                    case "3":
                        Printer.print("계좌를 선택해 주세요.(id pw 종류)\n (예 : abc bcd 포인트) : ");
                        selectAccount = Inputter.inpString().split(" ");

                        if (selectAccount.length != 3)
                        {
                            Printer.print("잘못된 입력입니다.\n");
                            break;
                        }

                        try {
                            menu.accountInquiry(selectAccount[0], converter(selectAccount[2]));
                            Printer.print("얼마 만큼 입금하시겠습니까?\n");
                            int amount = Inputter.inpInt();
                            menu.accountDeposit(amount, selectAccount[1]);
                            Printer.print("계좌에" + amount + "만큼 입금되어 현재 "+menu.getAccountBalance()+"원 있습니다.");
                        }

                        catch (NoTypeException e)
                        {
                            Printer.print("선택하신 계좌가 없습니다.\n");
                            break;
                        }

                        catch (EmptyInputException | TooManyInputException | NumberFormatException |
                               NegativeAmountException | ZeroAmountException e)
                        {
                            Printer.print("잘못된 입력입니다.\n");
                            break;
                        }

                        catch (InvalidPasswordException e)
                        {
                            Printer.print(e.getMessage()+"\n"); break;
                        }

                    case "4":
                        Printer.print("이름, 나이, 전화번호, 등록할 id, 설정할 비밀번호를 입력하세요.\n (예 : 홍길동 20 010-1234-1234 abc def) :");
                        String[] input = Inputter.inpString().split(" ");
                        if (input.length != 5) {
                            Printer.print("잘못된 입력입니다.\n");
                            break;
                        }
                        UserAccount makingAccount;
                        try {
                            makingAccount = menu.userAccountMake(menu.userIDMake(input[0], Integer.parseInt(input[1]), input[2]), input[3], input[4]);
                        }
                        catch (AgeException | PhoneNumberException | InvalidPhoneNumberFormat e)
                        {
                            Printer.print(e.getMessage());
                            break;
                        }

                        Printer.print("만들 계좌의 형식을 선택하세요. \n 종류 : Account(보통), Installment(예금), Minus(마이너스), Point(포인트), Saving(적금) (예 : Installment) : ");
                        input = Inputter.inpString().split(" ");
                        if (input.length != 1) {
                            Printer.print("잘못된 입력입니다.\n");
                            break;
                        }

                        String type = input[0];
                        final String FILE_PATH = "\\account"; final String FILE_EXTENSION = ".cbp";
                        String filename = FILE_PATH + type + makingAccount.getId() + FILE_EXTENSION;
                        try {
                            Serializer serializer = switch (type) {
                                case "Account" -> new Serializer<>(Account.class);
                                case "Installment" -> new Serializer<>(InstallmentAccount.class);
                                case "Minus" -> new Serializer<>(MinusAccount.class);
                                case "Point" -> new Serializer<>(PointAccount.class);
                                case "Saving" -> new Serializer<>(SavingAccount.class);
                                default -> throw new NoTypeException("존재하지 않는 형식입니다.");
                            };
                            serializer.saveObject(makingAccount,filename);
                        }
                        catch(NoTypeException e)
                        {
                            Printer.print(e.getMessage());
                        }

                        break;
                    case "5":
                        break LOOP;
                    default:
                        Printer.print("잘못된 입력입니다.\n");
                        break;
                }
            }
            catch (EmptyInputException e)
            {
                Printer.print("잘못된 입력입니다.\n");
            }
        }
    }

    public static void mainMenuPrint() throws IOException
    {
        OutputStream outputStream = System.out;
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bw = new BufferedWriter(outputStreamWriter);

        bw.write("*****************************************************************\n");
        bw.write("\t \t \t \t  원하시는 작업(번호)을 입력하세요.\n");
        bw.write("1. 인출 \t 2. 예금 조회 \t 3. 입금 \t 4. 계좌 개설 \t 5. 종료\n" );
        bw.write("*****************************************************************\n");
        bw.flush();
    }

    public static String converter(String str)
    {
        switch (str) {
            case "Point", "point", "포인트" -> {
                return "Point";
            }
            case "Saving", "saving", "적금" -> {
                return "Saving";
            }
            case "Minus", "minus", "마이너스" -> {
                return "Minus";
            }
            case "Installment", "installment", "예금" -> {
                return "Installment";
            }
            case "기본", "account", "Account", "보통" -> {
                return "Account";
            }
        }
        return "Error";
    }
}
