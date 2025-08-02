package com.vt.config;


import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DataSourceProperty;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.provider.AbstractDataSourceProvider;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Configuration
@AutoConfigureBefore({DynamicDataSourceAutoConfiguration.class, SpringBootConfiguration.class})
public class DataSourceConfig {

    @Resource
    private DynamicDataSourceProperties properties;

    @Lazy
    @Autowired
    private DataSource shardingDataSource;

    @Bean
    public DynamicDataSourceProvider dynamicDataSourceProvider(DefaultDataSourceCreator defaultDataSourceCreator) {
        Map<String, DataSourceProperty> datasource = properties.getDatasource();
        return new AbstractDataSourceProvider(defaultDataSourceCreator) {
            @Override
            public Map<String, DataSource> loadDataSources() {
//                Map<String, DataSource> dataSourceMap = new HashMap<>();
                Map<String, DataSource> dataSourceMap = createDataSourceMap(datasource);
                dataSourceMap.put("sharding",shardingDataSource);
                return dataSourceMap;
            }
        };
    }

    @Bean
    @Primary
    public DataSource dataSource(List<DynamicDataSourceProvider> providerList){
        DynamicRoutingDataSource routingDataSource = new DynamicRoutingDataSource(providerList);
        routingDataSource.setPrimary(properties.getPrimary());
        routingDataSource.setSeata(properties.getSeata());
        routingDataSource.setP6spy(properties.getP6spy());
        routingDataSource.setStrict(properties.getStrict());
        return routingDataSource;
    }
}
