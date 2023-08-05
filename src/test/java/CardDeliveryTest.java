import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.selector.ByText;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void shouldSomethingTest() {
        String city = DataGenerator.generateCity();
        String name = DataGenerator.generatorName();
        String phone = DataGenerator.generatePhoneNumber();
        String planingData = DataGenerator.generateDate(4, "dd.MM.yyyy");
        String changeDate = DataGenerator.generateDate(8, "dd.MM.yyyy");
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue(city);
        $("[data-test-id=date] input").doubleClick().sendKeys((planingData));
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $("[data-test-id=success-notification]").shouldHave(Condition.text("Встреча успешно запланирована на " + planingData));
        $(By.className("button")).click();
        $("[data-test-id=date] input").doubleClick().sendKeys((changeDate));
        $(By.className("button")).click();
        $(new ByText("Перепланировать")).click();
        $("[data-test-id=success-notification]").shouldHave(Condition.text("Встреча успешно запланирована на " + changeDate));

    }
}
