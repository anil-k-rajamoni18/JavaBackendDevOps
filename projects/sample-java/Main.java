import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World from Java!");
        final LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("Welcome to Jenkins Learning < o_o > , today: " +  localDateTime.toString());
    }
}
