package ru.skypro.homework.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Configuration class for setting up data source.
 * This class specifies the recommendation source and the default source.
 */
@Configuration
public class DataSourceConfiguration {
    /**
     * Configures and returns the primary (default) DataSource for the application.
     * It's marked as @Primary, so it will be the default DataSource injected when a DataSource is needed.
     *
     * @param properties The DataSourceProperties object containing the configuration for the DataSource.
     * @return The configured DataSource.
     */
    @Primary
    @Bean(name = "defaultDataSource")
    public DataSource defaultDataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    /**
     * Creates and returns a JdbcTemplate configured with the default DataSource.
     * This JdbcTemplate is the primary JdbcTemplate for the application
     * and will be injected when a JdbcTemplate is needed.
     *
     * @param dataSource The default DataSource, injected using the @Qualifier("defaultDataSource") annotation.
     * @return The configured JdbcTemplate.
     */
    @Bean(name = "defaultJdbcTemplate")
    public JdbcTemplate defaultJdbcTemplate(@Qualifier("defaultDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
