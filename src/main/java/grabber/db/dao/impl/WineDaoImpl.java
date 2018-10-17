package grabber.db.dao.impl;

import grabber.db.dao.WineDao;
import grabber.model.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class WineDaoImpl implements WineDao {

    private final String table = "TEST_WINE_DEV";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final RowMapper<Wine> MAPPER = new BeanPropertyRowMapper<>(Wine.class);

    @Autowired
    public WineDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void create(Wine wine) {
        // TODO: данные на сайтах разные (например где-то нет id с сайта) поэтому нужна универсальная проверка на наличиее записи!
        List<Wine> w = this.findByWineName(wine.getWineName());
        if (w.isEmpty()) {
            String query = "INSERT INTO WINE_GRABBING." + table + "(" +
                    "COUNTRY, REGION, MANUFACTURER, WINE_NAME,\n" +
                    "GRAPES, FOOD_SUGGESTION, WINE_STYLE, ALCOHOL_CONTENT,\n" +
                    "AGGREGATED_CRITIC, SOURCE_WEBSITE, WEBSITE_WINE_ID, PRODUCTION_YEAR,\n" +
                    "WINE_BODY, WINE_BODY_DESCRIPTION, ACIDITY, ACIDITY_DESCRIPTION,\n" +
                    "TASTE, COLOR, AROMA, WINE_TYPE, BOTTLE_VOLUME, PRICE, VENDOR_CODE,\n" +
                    "COLOR_DEPTH, SORTING_TEMPERATURE, MANUFACTURER_WEBSITE)\n" +
                    //"IMAGE_PATH)\n" +
                    "VALUES(" +
                    ":country, :region, :manufacturer, :wine_name,\n" +
                    ":grapes, :food_suggestion, :wine_style, :alcohol_content,\n" +
                    ":aggregated_critic, :source_website, :website_wine_id, :production_year,\n" +
                    ":wine_body, :wine_body_description, :acidity, :acidity_description,\n" +
                    ":taste, :color, :aroma, :wine_type, :bottle_volume, :price, :vendor_code,\n" +
                    ":color_depth, :sorting_temperature, :manufacturer_website)";
//                    ":image_path)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
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
                            .addValue("manufacturer_website", wine.getManufacturerWebsite()),
//                            .addValue("image_path", wine.getImagePath()),
                    keyHolder, new String[]{"id"});

            // add image path to linked table
            long id = keyHolder.getKey().longValue();
            wine.setId(id);
            this.saveImage(wine);
        }
    }

    @Override
    public List<Wine> findByWineName(String name) {
        String query = "SELECT * FROM WINE_GRABBING." + table + " WHERE WINE_NAME LIKE :wine_name";
        final SqlParameterSource params = new MapSqlParameterSource("wine_name", "%" + name + "%");
        return jdbcTemplate.query(query, params, MAPPER);
    }

    public void saveImage(Wine wine) {
        if (wine.getId() != null) {
            String query = "INSERT INTO WINE_GRABBING.IMAGE_LINK(" +
                    "WINE_ID, STORE_PATH)\n" +
                    "VALUES(" +
                    ":wine_id, :store_path)";

            jdbcTemplate.update(query, new MapSqlParameterSource()
            .addValue("wine_id", wine.getId())
            .addValue("store_path", wine.getImagePath()));
        }
    }


}
