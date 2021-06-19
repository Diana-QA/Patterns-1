package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;
import ru.netology.delivery.data.RegistrationByCardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    RegistrationByCardInfo user = DataGenerator.Registration.generateByCard("ru");

    @BeforeEach
    void setUp() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        open("http://localhost:9999/");
    }

    @Test
    void shouldSubmitCorrectForm() {
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getNumber());
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $$("button").find(exactText("Запланировать")).click();
        $$(".button__text").find(exactText("Перепланировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
