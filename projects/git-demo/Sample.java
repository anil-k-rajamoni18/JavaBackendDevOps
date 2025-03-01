import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;

interface Greet {
    String greetUser(final String msg);
}

public class Sample {
    public static void main(String[] args) {
        System.out.println("Welcome to Java 8 Features...");
        
        // Lambda 
        Greet greet = (msg) -> "Hello, " + msg;
        System.out.println(greet.greetUser("Kumar"));

        Predicate<Integer> voteEligiblePredicate = (age) -> age >= 18;
        System.out.println(voteEligiblePredicate.test(19));

        Supplier<Integer> otpGenerator = () -> {
            Random random = new Random();
            return 100000  + random.nextInt(900000);
        };
        System.out.println(otpGenerator.get());
    }
}
