package grabber.service.api.impl;

import com.fasterxml.jackson.databind.JsonNode;
import grabber.model.Wine;
import grabber.service.api.JsonDeserializerService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Deprecated
@Component
public class JsonDeserializerServiceImpl implements JsonDeserializerService {

    @Override
    public Wine deserializeJsonNodeToWineObject(Object object) {
        Wine wine = new Wine();
        JsonNode node = (JsonNode) object;

        wine.setCountry(node.findPath("wine").findPath("region").findPath("country").findValue("name") == null ? null : node.findPath("wine").findPath("region").findPath("country").findValue("name").asText());
        wine.setRegion(node.findPath("wine").findPath("region").findValue("name") == null ? null : node.findPath("wine").findPath("region").findValue("name").asText());
        wine.setManufacturer(node.findPath("winery").findValue("name") == null ? null : node.findPath("winery").findValue("name").asText());
        wine.setWineName(node.findPath("style").findValue("name") == null ? null : node.findPath("style").findValue("name").asText());

        wine.setGrapes(node.findPath("grapes").findValuesAsText("name")
                .stream().map(Object::toString).collect(Collectors.joining(", ")));

        wine.setFoodSuggestion(node.findPath("food").findValuesAsText("name")
                .stream().map(Object::toString).collect(Collectors.joining(", ")));

        wine.setWineStyle(node.findPath("style").findValue("description") == null ? null : node.findPath("style").findValue("description").asText());
        wine.setAlcoholContent(null);
        wine.setAggregatedCritic(node.findValue("ratings_average") == null ? null : node.findValue("ratings_average").floatValue());
        wine.setSourceWebsite("www.vivino.com");
        wine.setWebSiteWineId(node.findPath("wine").findValue("id") == null ? null : node.findPath("wine").findValue("id").asLong());
        wine.setProductionYear(node.findPath("vintage").findValue("year") == null ? null : node.findPath("vintage").findValue("year").asInt());
        wine.setWineBody(node.findValue("body") == null ? null : node.findValue("body").floatValue());
        wine.setWineBodyDescription(node.findValue("body_description") == null ? null : node.findValue("body_description").asText());
        wine.setAcidity(node.findPath("style").findValue("acidity") == null ? null : node.findPath("style").findValue("acidity").floatValue());
        wine.setAcidityDescription(node.findPath("style").findValue("acidity_description") == null ? null : node.findPath("style").findValue("acidity_description").asText());

        return wine;

    }
}
