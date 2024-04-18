package com.finidy.hibernateplayground;

import com.finidy.hibernateplayground.util.DataSourceWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceWrapperConfiguration {

    @Bean
    public DataSourceWrapper dataSourceWrapper() {
        return new DataSourceWrapper();
    }

}
