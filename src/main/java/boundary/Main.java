package boundary;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException
    {
        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        OutputStream outputStream = System.out;
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bw = new BufferedWriter(outputStreamWriter);

        String select = "";
        while (true)
        {
            mainMenu();
            select = ask();
            switch(select)
            {
                case "1":
                    message("계좌를 선택해 주세요.(id pw 종류) (예 : abc )");
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "EmptyOut":
                default:
                    bw.write("잘못된 입력입니다.");
            }
        }
    }

    public static void mainMenu() throws IOException
    {
        OutputStream outputStream = System.out;
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bw = new BufferedWriter(outputStreamWriter);

        bw.write("**********************************************\n");
        bw.write("\t \t 원하시는 작업(번호)을 입력하세요.\n");
        bw.write("1. 인출 \t 2. 예금 조회 \t 3. 이체 \t 4. 입금\n");
        bw.write("**********************************************\n");
        bw.flush();
    }

    public static void message(String message) throws IOException
    {
        OutputStream outputStream = System.out;
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bw = new BufferedWriter(outputStreamWriter);

        bw.write(message+"\n");
        bw.flush();
    }

    public static String ask() throws IOException {
        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String value = br.readLine();
        if (value.isBlank() || value.isEmpty())
        {
            return "EmptyOut";
        }
        return value.trim();
    }

}
