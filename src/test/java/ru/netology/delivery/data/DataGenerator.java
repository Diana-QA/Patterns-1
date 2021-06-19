package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    public DataGenerator() {
    }

    public static class Registration {
        private Registration() {}

        public static RegistrationByCardInfo generateByCard(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationByCardInfo(
                    faker.address().cityName(),
                    LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    faker.name().firstName() + " " + faker.name().lastName(),
                    faker.phoneNumber().phoneNumber());
        }
    }
}
