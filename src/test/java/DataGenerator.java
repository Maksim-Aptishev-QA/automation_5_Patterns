import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DataGenerator {
    static Faker faker = new Faker(new Locale("ru"));

    public static String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String generatorName(){
        return faker.name().fullName();
    }

    public static String generatePhoneNumber() {
        return faker.numerify("+7(###)###-##-##");
    }

    public static final List<String> cities = Arrays.asList("Москва", "Нижний Новгород", "Казань", "Самара", "Архангельск", "Владикавказ", "Владивосток", "Хабаровск", "Новосибирск");

    public static String generateCity() {
        return cities.get(faker.random().nextInt(cities.size()));
    }

}

