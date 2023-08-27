package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;

public class Inputter {
    InputStream inputStream = System.in;
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader bf = new BufferedReader(inputStreamReader);

    public int inpInt() throws IOException
    {
        return Integer.parseInt(bf.readLine());
    }

    public long inpLong() throws  IOException
    {
        return Long.parseLong(bf.readLine());
    }

    public String inpString() throws IOException
    {
        return bf.readLine();
    }

}
