package grabber.service.api.impl;

import com.fasterxml.jackson.databind.JsonNode;
import grabber.common.Utils;
import grabber.db.dao.WineDao;
import grabber.model.Wine;
import grabber.service.api.JsonDeserializerService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JsonDeserializerServiceImpl implements JsonDeserializerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonDeserializerServiceImpl.class);

    @Value("${image.download.path.vivino}")
    String imgPath;

    @Autowired
    WineDao wineDao;

    @Override
    public Wine deserializeJsonNodeToWineObject(Object object) {
        Wine wine = new Wine();
        JsonNode node = (JsonNode) object;

        String country = Utils.nullableGetTextFromJsonNode(node.findPath("wine").findPath("region").findPath("country").findValue("name"), false);
        String region = Utils.nullableGetTextFromJsonNode(node.findPath("wine").findPath("region").findValue("name"), false);
        String manufacturer = Utils.nullableGetTextFromJsonNode(node.findPath("winery").findValue("name"), false);
        String wineName = Utils.nullableGetTextFromJsonNode(node.findPath("vintage").findValue("name"), false);
        String grapes = node.findPath("grapes").findValuesAsText("name")
                .stream().map(Object::toString).collect(Collectors.joining(", "));
        String foodSuggestion = node.findPath("food").findValuesAsText("name")
                .stream().map(Object::toString).collect(Collectors.joining(", "));
        String wineStyle = Utils.nullableGetTextFromJsonNode(node.findPath("style").findValue("description"), false);
        Float aggregatedCritic = NumberUtils.createFloat(Utils.nullableGetTextFromJsonNode(node.findValue("ratings_average"), true));
        Integer productionYear = NumberUtils.createInteger(StringUtils.defaultIfEmpty(Utils.nullableGetTextFromJsonNode(node.findPath("vintage").findValue("year"), false)
            .replaceAll("[^\\d]", ""), null));
        Float wineBody = NumberUtils.createFloat(Utils.nullableGetTextFromJsonNode(node.findValue("body"), true));
        String wineBodyDescription = Utils.nullableGetTextFromJsonNode(node.findValue("body_description"), false);
        Float acidity = NumberUtils.createFloat(Utils.nullableGetTextFromJsonNode(node.findPath("style").findValue("acidity"), true));
        String acidityDescription = Utils.nullableGetTextFromJsonNode(node.findPath("style").findValue("acidity_description"), false);
        String wineType = Utils.nullableGetTextFromJsonNode(node.findPath("style").findValue("varietal_name"), false);
        String imagePath = downloadImage(this.imageSorting(node.findPath("image").findPath("variations")));

        wine.setCountry(country);
        wine.setRegion(region);
        wine.setManufacturer(manufacturer);
        wine.setWineName(wineName);
        wine.setGrapes(grapes);
        wine.setFoodSuggestion(foodSuggestion);
        wine.setWineStyle(wineStyle);
        wine.setAggregatedCritic(aggregatedCritic);
        wine.setProductionYear(productionYear);
        wine.setWineBody(wineBody);
        wine.setWineBodyDescription(wineBodyDescription);
        wine.setAcidity(acidity);
        wine.setAcidityDescription(acidityDescription);
        wine.setWineType(wineType);
        wine.setImagePath(imagePath);

        return wine;

    }

    private String imageSorting(JsonNode node) {
        String result = null;
        List<String> list = new ArrayList<>();
        if (node != null) {
            node.forEach(i -> {
                list.add(i.toString());
            });
            result = list.stream().findFirst().get().replace("\"", "");
            result = "https:" + result;
        }
        return result;
    }

    @SuppressWarnings("Duplicates")
    private String downloadImage(String srcUrl) {
        String result = null;
        try {
            if (srcUrl != null || !srcUrl.isEmpty()) {
                URL imageUrl = new URL(srcUrl);
                BufferedImage img = ImageIO.read(imageUrl);
                if (img != null) {
                    String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(25), "jpg");
                    ImageIO.write(img, "jpg", new File(imgPath + name));
                    result = imgPath + name;
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }
}
