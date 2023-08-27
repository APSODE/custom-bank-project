package controller;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Printer {
    OutputStream outputStream = System.out;
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
    BufferedWriter bw = new BufferedWriter(outputStreamWriter);

    public void print(String value) throws IOException
    {
        bw.write(value);
        bw.flush();
    }
}
