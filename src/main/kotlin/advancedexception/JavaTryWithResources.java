package advancedexception;

// Java7 부터 제공하는 try-with-resources 구문을 사용하면 자동으로 리소스를 close 처리

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JavaTryWithResources {
    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter("test.txt")) {
            writer.println("Hello World");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
