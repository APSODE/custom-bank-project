package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;

public class Inputter {
    static InputStream inputStream = System.in;
    static InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    static BufferedReader bf = new BufferedReader(inputStreamReader);

    public static int inpInt() throws IOException
    {
        return Integer.parseInt(bf.readLine());
    }

    public static long inpLong() throws  IOException
    {
        return Long.parseLong(bf.readLine());
    }

    public static String inpString() throws IOException
    {
        return bf.readLine();
    }

}
