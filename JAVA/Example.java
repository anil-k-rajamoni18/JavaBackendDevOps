import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.io.Console;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Example {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Hello I'm thread...");
        r.run();

        Predicate<Integer> predicate = (n) -> n % 2 == 0;
        System.out.println(predicate.test(17));
        System.out.println(predicate.test(26));


        // 
        Function<String, Integer> fun = (name) -> name.length();
        System.out.println(fun.apply("Radhika"));

        Consumer<String> consumer = (message) -> System.out.println("Hello " + message);
        consumer.accept("ram");


        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<String> filteredNames = names.stream()
                                    .filter(name -> name.startsWith("A"))
                                    .collect(Collectors.toList());

        System.out.println(filteredNames);

        // MAP 
        names.stream()
            .map(name -> name.toLowerCase())
            .forEach(System.out::println); // name -> System.out.println(name)
        

            // optional 
            Optional<String> name = Optional.ofNullable(getName());
            System.out.println(name.isPresent());
            System.out.println(name.orElse("Default Name"));

            LocalDate today = LocalDate.now();
            System.out.println(today);
            LocalDate tomorrow = today.plusDays(1);
            System.out.println("Tomorrow's date: " + tomorrow);

    }

    public static String getName() {
        return null;
    }
}