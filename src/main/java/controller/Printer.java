package controller;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Printer {
    static OutputStream outputStream = System.out;
    static OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
    static BufferedWriter bw = new BufferedWriter(outputStreamWriter);

    public static void print(String value) throws IOException
    {
        bw.write(value);
        bw.flush();
    }
    public static void print(long value) throws IOException
    {
        bw.write((int) value);
        bw.flush();
    }
    public static void print(int value) throws IOException
    {
        bw.write(value);
        bw.flush();
    }
}
