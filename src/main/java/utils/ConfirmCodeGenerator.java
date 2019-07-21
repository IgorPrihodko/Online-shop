package utils;

public class ConfirmCodeGenerator {

    public static String generateCode() {
        return String.valueOf(Math.round(Math.random() * 10000));
    }
}
