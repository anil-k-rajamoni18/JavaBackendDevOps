import java.util.function.Predicate;

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
    }
}
