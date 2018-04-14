package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by WangYx on 2018/4/13.
 */
@Configuration
@MapperScan(basePackages = {"com.example.demo.mapper1"},
                sqlSessionFactoryRef = "sqlSessionFactoryDev01",
                sqlSessionTemplateRef = "sqlSessionTemplate1")
public class MybatisDev1Config {

    @Autowired
    @Qualifier("dev01DS")
    private DataSource dev01;

    @Bean(name = "sqlSessionFactoryDev01")
    @Primary
    public SqlSessionFactory sqlSessionFactoryDev01()throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dev01);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/example/demo/mapper1/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "dataSourceDev01TransactionManager")
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager()throws Exception{
        return new DataSourceTransactionManager(dev01);
    }

    @Bean(name = "sqlSessionTemplate1")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactoryDev01()); // 使用上面配置的Factory
        return template;
    }
}
