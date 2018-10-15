package grabber.config;

import org.springframework.beans.factory.annotation.Value;

public class ApplicationConfigProperties {
    
    @Value("${datasource.driver-class-name}")
    private String jdbcDriverClassName;
    
    @Value("${datasource.url}")
    private String jdbcUrl;
    
    @Value("${datasource.username}")
    private String jdbcUser;
    
    @Value("${datasource.password}")
    private String jdbcPassword;

    @Value("${datasource.database.min.pool.size}")
    private int minPoolSize;

    @Value("${datasource.database.max.pool.size}")
    private int maxPoolSize;

    @Value("${datasource.database.query.timeout}")
    private int queryTimeout;

    @Value("${datasource.database.fetch_size}")
    private int fetchSize;


    public String getJdbcDriverClassName() {
        return jdbcDriverClassName;
    }

    public void setJdbcDriverClassName(String jdbcDriverClassName) {
        this.jdbcDriverClassName = jdbcDriverClassName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public void setJdbcUser(String jdbcUser) {
        this.jdbcUser = jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public int getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(int minPoolSize) {
        this.minPoolSize = minPoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getQueryTimeout() {
        return queryTimeout;
    }

    public void setQueryTimeout(int queryTimeout) {
        this.queryTimeout = queryTimeout;
    }

    public int getFetchSize() {
        return fetchSize;
    }

    public void setFetchSize(int fetchSize) {
        this.fetchSize = fetchSize;
    }
}
