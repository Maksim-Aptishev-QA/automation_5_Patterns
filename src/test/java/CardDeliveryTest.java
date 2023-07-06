import com.codeborne.selenide.Condition;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldSomethingTest1()  {
        String city = DataGenerator.generateCity();
        String name = DataGenerator.generatorName();
        String phone = DataGenerator.generatePhoneNumber();
        String planingData = DataGenerator.generateDate(4,"dd.MM.yyyy");
        String changeDate = DataGenerator.generateDate(8,"dd.MM.yyyy");
        $("[data-test-id=city] input") .setValue(city);
        $("[data-test-id=date] input") .doubleClick().sendKeys((planingData));
        $("[data-test-id=name] input") .setValue(name);
        $("[data-test-id=phone] input") .setValue(phone);
        $("[data-test-id=agreement]") .click();
        $(By.className("button")) .click();
        $("[data-test-id=success-notification]").shouldHave(Condition.text("Встреча успешно запланирована на " + planingData));
        $(By.className("button")) .click();
        $("[data-test-id=date] input") .doubleClick().sendKeys((changeDate));
        $(By.className("button")) .click();
        $(new ByText("Перепланировать")).click();
        $("[data-test-id=success-notification]").shouldHave(Condition.text("Встреча успешно запланирована на " + changeDate));

    }
}
