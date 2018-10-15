package grabber.service.ui.impl;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import grabber.common.UiUtils;
import grabber.common.Utils;
import grabber.db.dao.WineDao;
import grabber.model.Wine;
import grabber.service.ui.UiGrabWineStyleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Component
public class UiGrabWineStyleServiceImpl implements UiGrabWineStyleService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UiGrabWineStyleServiceImpl.class);

    @Autowired
    WebDriver webDriver;

    @Autowired
    WineDao wineDao;

    public void grabItems() {
        int itemsCount = selectFilterSettings();
        int processedCount = 0;
        SelenideElement parentContainer = $(byClassName("items-container"));
        ElementsCollection items;

        //TODO: логика сбора данных переведена на постраничную, сделать простой счетчик вин + вывод текущей страницы.
        do {
            items = $$(byClassName("item-block"));
            items.forEach(item -> {
                Wine wine = grabInfoFromPage(item);
                wineDao.create(wine);
            });
            processedCount += items.size();
            LOGGER.info("Обработанно результатов: " + processedCount + " из: " + itemsCount);
            $$(byClassName("next")).findBy(exist).click();
        } while (items.size() != itemsCount);

    }

    private int selectFilterSettings() {
        // agree with 18 years age
        confirmAge();
        $$(byClassName("has-sublevel")).findBy(text("Вино")).click();
        $$(byClassName("button")).findBy(text("Показать все вина")).click();
        int wineCount = Integer.parseInt($$(byClassName("small"))
                .findBy(text("Найдено"))
                .getText()
                .replaceAll("[^\\d]", ""));

        LOGGER.info("Число найденых результатов: " + wineCount);

        return wineCount;
    }


    private Wine grabInfoFromPage(SelenideElement element) {
        Wine wine = new Wine();

        webDriver.get(element.$(byClassName("item-header")).$(By.tagName("a")).getAttribute("href"));

        String country = $(byClassName("main-info")).$$(By.tagName("li")).findBy(text("Регион")).$$(By.tagName("a")).texts()
                .stream().findFirst().get();
        String region = UiUtils.checkAndExtractText($(byClassName("main-info")).$$(By.tagName("li")).findBy(text("Регион")).$(byClassName("links")));
        String manufacturer = UiUtils.checkAndExtractText($(byClassName("main-info")).$$(By.tagName("li")).findBy(text("Производитель")).$(byClassName("links")));
        String wineName = UiUtils.checkAndExtractText($(byClassName("main-header"))
                .$(By.tagName("h1")));
        String grapes = UiUtils.checkAndExtractText($(byClassName("main-info")).$$(By.tagName("li")).findBy(text("Виноград")).$(byClassName("links")));
        String foodSuggestion = UiUtils.checkAndExtractText($$(byClassName("description-block")).findBy(text("Гастрономические сочетания")).$(By.tagName("p")));
        String wineStyle = UiUtils.checkAndExtractText($$(byClassName("articles-container")).findBy(text("Интересные факты"))
                .$(byClassName("collapse-content-processed")));
        Float alcoholContent = NumberUtils.createFloat(UiUtils.checkAndExtractText($(byClassName("main-info")).$$(By.tagName("li")).findBy(text("Крепость"))
                .$(byClassName("links")))
                .replaceAll("[^\\d,.]", ""));
        Float aggregatedCritic = NumberUtils.createFloat(UiUtils.checkAndExtractText($(byClassName("rating-text-big")).$(byClassName("text"))));
        Integer productionYear = NumberUtils.createInteger(StringUtils.defaultIfEmpty(UiUtils.checkAndExtractText($(byClassName("main-header"))
                .$(By.tagName("h1")))
                .replaceAll("[^\\d(17|20)\\d{2}$]", ""), null));
        String wineBodyDescription = Utils.nullableReplace(UiUtils.checkAndExtractText($$(byClassName("list-characteristics")).findBy(exist)
                .$$(By.tagName("li")).findBy(text("Тело/Насыщенность"))), "Тело/Насыщенность:");
        String taste = UiUtils.checkAndExtractText($$(byClassName("description-block")).findBy(text("Вкус")).$(By.tagName("p")));
        String color = UiUtils.checkAndExtractText($$(byClassName("description-block")).findBy(text("Цвет")).$(By.tagName("p")));
        String aroma = UiUtils.checkAndExtractText($$(byClassName("description-block")).findBy(text("Аромат")).$(By.tagName("p")));
        String wineType = UiUtils.checkAndExtractText($(byClassName("main-info")).$$(By.tagName("li")).findBy(text("Вино")).$(byClassName("links")));
        Float bottleVolume = NumberUtils.createFloat(UiUtils.checkAndExtractText($(byClassName("main-info")).$$(By.tagName("li")).findBy(text("Объем"))
                .$(byClassName("links")))
                .replaceAll("[^\\d.,]", ""));
        Integer price = NumberUtils.createInteger(UiUtils.checkAndExtractText($(byClassName("price-container")).$(byClassName("price")))
                .replaceAll("[^\\d]", ""));
        String vendorCode = StringUtils.defaultString($$(byClassName("bg-text")).findBy(attribute("title", "Артикул")).getText()
                .replace("Артикул:", ""), null);
        String colorDepth = Utils.nullableReplace(UiUtils.checkAndExtractText($$(byClassName("list-characteristics")).findBy(exist)
                .$$(By.tagName("li")).findBy(text("Глубина цвета"))), "Глубина цвета:");
        String sortingTemperature = Utils.nullableReplace(UiUtils.checkAndExtractText($$(byClassName("list-characteristics")).findBy(exist)
                .$$(By.tagName("li")).findBy(text("Температура сервировки"))), "Температура сервировки:");
        String manufacturerWebsite = UiUtils.checkAndExtractText($$(byClassName("list-characteristics")).findBy(text("Сайт производителя")).$(byClassName("text")));

        wine.setCountry(country);
        wine.setRegion(region);
        wine.setManufacturer(manufacturer);
        wine.setWineName(wineName);
        wine.setGrapes(grapes);
        wine.setFoodSuggestion(foodSuggestion);
        wine.setWineStyle(wineStyle);
        wine.setAlcoholContent(alcoholContent);
        wine.setAggregatedCritic(aggregatedCritic);
        wine.setSourceWebsite("https://winestyle.ru/");
        wine.setWebSiteWineId(null);
        wine.setProductionYear(productionYear);
        wine.setWineBody(null);
        wine.setWineBodyDescription(wineBodyDescription);
        wine.setAcidity(null);
        wine.setAcidityDescription(null);
        wine.setTaste(taste);
        wine.setColor(color);
        wine.setAroma(aroma);
        wine.setWineType(wineType);
        wine.setBottleVolume(bottleVolume);
        wine.setPrice(price);
        wine.setVendorCode(vendorCode);
        wine.setColorDepth(colorDepth);
        wine.setSortingTemperature(sortingTemperature);
        wine.setManufacturerWebsite(manufacturerWebsite);

        LOGGER.info(wine.toString());
        Selenide.back();

        return wine;
    }

    private void confirmAge() {
        SelenideElement confirmAgePopup = $$(byClassName("popup-container")).findBy(text("Внимание"));
        if (confirmAgePopup.is(visible)) {
            confirmAgePopup.$$(byClassName("button")).findBy(text("Мне исполнилось 18 лет")).click();
        }
    }
}
