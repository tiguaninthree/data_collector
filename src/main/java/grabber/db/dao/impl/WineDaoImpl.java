package grabber.db.dao.impl;

import grabber.db.dao.WineDao;
import grabber.model.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class WineDaoImpl implements WineDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public WineDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Wine wine) {
        String query = "INSERT INTO WINE_GRABBING.WINE(" +
                "COUNTRY, REGION, MANUFACTURER, WINE_NAME,\n" +
                "GRAPES, FOOD_SUGGESTION, WINE_STYLE, ALCOHOL_CONTENT,\n" +
                "AGGREGATED_CRITIC, SOURCE_WEBSITE, WEBSITE_WINE_ID, PRODUCTION_YEAR,\n" +
                "WINE_BODY, WINE_BODY_DESCRIPTION, ACIDITY, ACIDITY_DESCRIPTION,\n" +
                "TASTE, COLOR, AROMA, WINE_TYPE, BOTTLE_VOLUME, PRICE, VENDOR_CODE,\n" +
                "COLOR_DEPTH, SORTING_TEMPERATURE, MANUFACTURER_WEBSITE,\n" +
                "IMAGE_PATH)\n" +
                "VALUES(" +
                ":country, :region, :manufacturer, :wine_name,\n" +
                ":grapes, :food_suggestion, :wine_style, :alcohol_content,\n" +
                ":aggregated_critic, :source_website, :website_wine_id, :production_year,\n" +
                ":wine_body, :wine_body_description, :acidity, :acidity_description,\n" +
                ":taste, :color, :aroma, :wine_type, :bottle_volume, :price, :vendor_code,\n" +
                ":color_depth, :sorting_temperature, :manufacturer_website,\n" +
                ":image_path)";
        jdbcTemplate.update(query, new MapSqlParameterSource()
                .addValue("country", wine.getCountry())
                .addValue("region", wine.getRegion())
                .addValue("manufacturer", wine.getManufacturer())
                .addValue("wine_name", wine.getWineName())
                .addValue("grapes", wine.getGrapes())
                .addValue("food_suggestion", wine.getFoodSuggestion())
                .addValue("wine_style", wine.getWineStyle())
                .addValue("alcohol_content", wine.getAlcoholContent())
                .addValue("aggregated_critic", wine.getAggregatedCritic())
                .addValue("source_website", wine.getSourceWebsite())
                .addValue("website_wine_id", wine.getWebSiteWineId())
                .addValue("production_year", wine.getProductionYear())
                .addValue("wine_body", wine.getWineBody())
                .addValue("wine_body_description", wine.getWineBodyDescription())
                .addValue("acidity", wine.getAcidity())
                .addValue("acidity_description", wine.getAcidityDescription())
                .addValue("taste", wine.getTaste())
                .addValue("color", wine.getColor())
                .addValue("aroma", wine.getAroma())
                .addValue("wine_type", wine.getWineType())
                .addValue("bottle_volume", wine.getBottleVolume())
                .addValue("price", wine.getPrice())
                .addValue("vendor_code", wine.getVendorCode())
                .addValue("color_depth", wine.getColorDepth())
                .addValue("sorting_temperature", wine.getSortingTemperature())
                .addValue("manufacturer_website", wine.getManufacturerWebsite())
                .addValue("image_path", wine.getImagePath()));
    }

}
