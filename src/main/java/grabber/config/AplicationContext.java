package grabber.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.beans.PropertyVetoException;

public class AplicationContext {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @ConfigurationProperties("application.properties")
    public ComboPooledDataSource createC3P0Pool() {
        ComboPooledDataSource source = new ComboPooledDataSource();
        ApplicationConfigProperties properties = new ApplicationConfigProperties();

        try {
            source.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException e) {
            return null;
        }
        source.setPreferredTestQuery("SELECT 1 + 1");
        source.setJdbcUrl(properties.getJdbcUrl());
        source.setUser(properties.getJdbcUser());
        source.setPassword(properties.getJdbcPassword());
        source.setMaxPoolSize(properties.getMaxPoolSize());
        source.setMinPoolSize(properties.getMinPoolSize());
        source.setInitialPoolSize(properties.getMinPoolSize());
        source.setMaxIdleTime(30);
        source.setTestConnectionOnCheckin(true);
        source.setIdleConnectionTestPeriod(1);
        source.setCheckoutTimeout(0);

        return source;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(createC3P0Pool());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(createC3P0Pool());
    }
}
