package helper;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class Helper {

    private final static Faker FAKER = new Faker();

    public static UUID getRandomUUID() {return UUID.randomUUID();}
}
