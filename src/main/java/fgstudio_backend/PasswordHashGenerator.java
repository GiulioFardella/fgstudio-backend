package fgstudio_backend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Console;
import java.util.Scanner;

public class PasswordHashGenerator {

    public static void main(String[] args) {
        String password;

        Console console = System.console();

        if (console != null) {
            char[] chars = console.readPassword("Password admin: ");
            password = new String(chars);
        } else {
            System.out.print("Password admin: ");
            password = new Scanner(System.in).nextLine();
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println("\nHASH:");
        System.out.println(encoder.encode(password));
    }
}