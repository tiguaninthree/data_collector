package grabber.service.api.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import grabber.common.Utils;
import grabber.db.dao.WineDao;
import grabber.model.Wine;
import grabber.service.api.VivinoItemGrabberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Deprecated
@Component
public class VivinoItemGrabberServiceImpl implements VivinoItemGrabberService {

    @Value("${grabber.request.url.vivino.red}")
    private String redWine;

    @Value("${grabber.request.url.vivino.white}")
    private String whiteWine;

    @Value("${grabber.request.url.vivino.sparkling}")
    private String sparklingWine;

    @Value("${grabber.request.url.vivino.rose}")
    private String roseWine;

    @Value("${grabber.request.url.vivino.dessert}")
    private String dessertWine;

    @Value("${grabber.request.url.vivino.port}")
    private String portWine;


    @Autowired
    JsonDeserializerServiceImpl jsonDeserializerService;

    @Autowired
    WineDao wineDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(VivinoItemGrabberServiceImpl.class);

    @Override
    public void grabItemsForWineType(String wineType) {
        int pageNum = 1;
        ResponseEntity<JsonNode> response;
        do {
            response = new RestTemplate()
                    .exchange(
                            Utils.replaceQueryParameter(wineType, "page", pageNum),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            JsonNode.class
                    );
            pageNum++;
            if (response != null) {
                LOGGER.info("Page number: " + pageNum);
                ArrayNode root = (ArrayNode) response.getBody().get("explore_vintage").get("records");
                root.forEach(item -> {
                    Wine wine = jsonDeserializerService.deserializeJsonNodeToWineObject(item.findParent("vintage"));
                    LOGGER.info("Wine info: " + wine.toString());
                    wineDao.create(wine);
                });

            }

            //TODO: отладочный костыль. Заменить проверкой на дублирование (после 81 страницы)!
        } while (pageNum != 10);

    }

    @Override
    // TODO: вина ищутся по отдельным фильтрам - действует ограничение.
    public void grabAllWineTypes() {
        grabItemsForWineType(redWine);
        grabItemsForWineType(whiteWine);
        grabItemsForWineType(sparklingWine);
        grabItemsForWineType(roseWine);
        grabItemsForWineType(dessertWine);
        grabItemsForWineType(portWine);
    }


    private String pageIncrement(int pageNum, String wineType) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(wineType);
        urlBuilder.replaceQueryParam("page", pageNum);
        String result = urlBuilder.build().toUriString();
        return result;
    }


}
