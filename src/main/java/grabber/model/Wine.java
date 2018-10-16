package grabber.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Wine {

    private Long id;
    private String country;
    private String region;
    private String manufacturer;
    private String wineName;
    private String grapes;
    private String foodSuggestion;
    private String wineStyle;
    private Float alcoholContent;
    private Float aggregatedCritic;
    private String sourceWebsite;
    private Long webSiteWineId;
    private Integer productionYear;
    private Float wineBody;
    private String wineBodyDescription;
    private Float acidity;
    private String acidityDescription;
    private String taste;
    private String color;
    private String aroma;
    private String wineType;
    private Float bottleVolume;
    private Integer price;
    private String vendorCode;
    private String colorDepth;
    private String manufacturerWebsite;
    private String sortingTemperature;
    private String imagePath;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    public String getGrapes() {
        return grapes;
    }

    public void setGrapes(String grapes) {
        this.grapes = grapes;
    }

    public String getFoodSuggestion() {
        return foodSuggestion;
    }

    public void setFoodSuggestion(String foodSuggestion) {
        this.foodSuggestion = foodSuggestion;
    }

    public String getWineStyle() {
        return wineStyle;
    }

    public void setWineStyle(String wineStyle) {
        this.wineStyle = wineStyle;
    }

    public Float getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(Float alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public Float getAggregatedCritic() {
        return aggregatedCritic;
    }

    public void setAggregatedCritic(Float aggregatedCritic) {
        this.aggregatedCritic = aggregatedCritic;
    }

    public String getSourceWebsite() {
        return sourceWebsite;
    }

    public void setSourceWebsite(String sourceWebsite) {
        this.sourceWebsite = sourceWebsite;
    }

    public Long getWebSiteWineId() {
        return webSiteWineId;
    }

    public void setWebSiteWineId(Long webSiteWineId) {
        this.webSiteWineId = webSiteWineId;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Float getWineBody() {
        return wineBody;
    }

    public void setWineBody(Float wineBody) {
        this.wineBody = wineBody;
    }

    public String getWineBodyDescription() {
        return wineBodyDescription;
    }

    public void setWineBodyDescription(String wineBodyDescription) {
        this.wineBodyDescription = wineBodyDescription;
    }

    public Float getAcidity() {
        return acidity;
    }

    public void setAcidity(Float acidity) {
        this.acidity = acidity;
    }

    public String getAcidityDescription() {
        return acidityDescription;
    }

    public void setAcidityDescription(String acidityDescription) {
        this.acidityDescription = acidityDescription;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAroma() {
        return aroma;
    }

    public void setAroma(String aroma) {
        this.aroma = aroma;
    }

    public String getWineType() {
        return wineType;
    }

    public void setWineType(String wineType) {
        this.wineType = wineType;
    }

    public Float getBottleVolume() {
        return bottleVolume;
    }

    public void setBottleVolume(Float bottleVolume) {
        this.bottleVolume = bottleVolume;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getColorDepth() {
        return colorDepth;
    }

    public void setColorDepth(String colorDepth) {
        this.colorDepth = colorDepth;
    }

    public String getManufacturerWebsite() {
        return manufacturerWebsite;
    }

    public void setManufacturerWebsite(String manufacturerWebsite) {
        this.manufacturerWebsite = manufacturerWebsite;
    }

    public String getSortingTemperature() {
        return sortingTemperature;
    }

    public void setSortingTemperature(String sortingTemperature) {
        this.sortingTemperature = sortingTemperature;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
