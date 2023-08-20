package IOController;

import java.util.Scanner;

public class IOController<T> {
    private Scanner sc_object;

    public IOController() {
        this.sc_object = new Scanner(System.in);
    }

    public T input(boolean change_line) throws {
        String input_data;

        if (change_line) {
            input_data = this.sc_object.next();
        } else {
            input_data = this.sc_object.nextLine();
        }

        
    }

    public static void print(String print_target) {
        System.out.print(print_target);
    }

    public static void println(String print_target) {
        System.out.println(print_target);
    }
}