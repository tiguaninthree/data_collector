//package grabber.service.ui.impl;
//
//import com.codeborne.selenide.ElementsCollection;
//import com.codeborne.selenide.Selenide;
//import com.codeborne.selenide.SelenideElement;
//import grabber.model.Wine;
//import grabber.service.ui.UiGrabbingService;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//import static com.codeborne.selenide.Condition.*;
//import static com.codeborne.selenide.Selectors.byClassName;
//import static com.codeborne.selenide.Selectors.byText;
//import static com.codeborne.selenide.Selenide.$;
//import static com.codeborne.selenide.Selenide.$$;
//
//@Component
//public class UiGrabbingServiceImpl implements UiGrabbingService {
//
//    public List<Wine> grabFromVivino() {
//        $(byText("Show wines")).click();
//        setFilterForAllTypes();
//        uploadAllItemsByScrolling();
//
//
//        return null;
//
//    }
//
//    /**
//     * Настройка фильтра на максимальное количество вин.
//     */
//    private void setFilterForAllTypes() {
//        SelenideElement parentFilter = $(byClassName("explorerPageFilters__filters--2_MsE"));
//        // wine types
//        parentFilter.$(byClassName("filterByWineType__items--2GBgf"))
//                .$$(byClassName("pill__inner--2uty5"))
//                .findBy(text("Red")).click();
//
//        // TODO: add price range (dragAndDrop)
//
//        // user rating
//        $$(byClassName("filterByRating__item--2IwLo"))
//                .findBy(text("Any rating")).click();
//    }
//
//    private void uploadAllItemsByScrolling() {
//        SelenideElement container = $(byClassName("explorerPage__results--3wqLw"));
//
////        while (true) {
//            ElementsCollection items = $$(byClassName("explorerPageResults__explorerCard--3q6Qe")).filterBy(visible);
//            Selenide.sleep(10000);
//            System.out.println(items.size() + "==================================");
//            items.forEach(i -> i.scrollTo());
//            Selenide.sleep(10000);
//        //}
//    }
//
//}
