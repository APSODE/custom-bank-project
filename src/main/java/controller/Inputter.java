package controller;

import exceptions.EmptyInputException;
import exceptions.TooManyInputException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;

public class Inputter {
    static InputStream inputStream = System.in;
    static InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    static BufferedReader bf = new BufferedReader(inputStreamReader);

    public static int inpInt() throws IOException, EmptyInputException, NumberFormatException, TooManyInputException {
        String value = bf.readLine();
        if (value.isBlank() || value.isEmpty())
        {
            throw new EmptyInputException("입력 값이 없습니다.");
        }
        if (value.split(" ").length != 1)
        {
            throw new TooManyInputException("입력 값이 너무 많습니다.");
        }
        return Integer.parseInt(value);
    }
    public static long inpLong() throws IOException, EmptyInputException, NumberFormatException {
        String value = bf.readLine();
        if (value.isBlank() || value.isEmpty())
        {
            throw new EmptyInputException("입력 값이 없습니다.");
        }
        return Long.parseLong(value);
    }

    public static String inpString() throws IOException, EmptyInputException {
        String value = bf.readLine();
        if (value.isBlank() || value.isEmpty())
        {
            throw new EmptyInputException("입력 값이 없습니다.");
        }
        return value;
    }

}
