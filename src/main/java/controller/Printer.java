package controller;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Printer {
    static OutputStream outputStream = System.out;
    static OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
    static BufferedWriter bw = new BufferedWriter(outputStreamWriter);

    public static void print(String value)
    {
        try {
            bw.write(value);
            bw.flush();
        }
        catch (IOException e)
        {
            print("입출력이 잘못되었습니다.");
        }
    }

    public static void print(long value)
    {
        try {
            bw.write((int) value);
            bw.flush();
        }
        catch (IOException e)
        {
            print("입출력이 잘못되었습니다.");
        }
    }
    public static void print(int value)
    {
        try {
            bw.write(value);
            bw.flush();
        }
        catch (IOException e)
        {
            print("입출력이 잘못되었습니다.");
        }
    }
}
