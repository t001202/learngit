package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by WangYx on 2018/4/13.
 */
@Configuration
@MapperScan(basePackages = {"com.example.demo.mapper2"},
                sqlSessionFactoryRef = "sqlSessionFactoryDev02",
                sqlSessionTemplateRef = "sqlSessionTemplate2")
public class MybatisDev2Config {

    @Autowired
    @Qualifier("dev02DS")
    private DataSource dev02;

    @Bean(name = "sqlSessionFactoryDev02")
    public SqlSessionFactory sqlSessionFactoryDev02()throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dev02);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/example/demo/mapper2/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "dataSourceDev02TransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager()throws Exception{
        return new DataSourceTransactionManager(dev02);
    }

    @Bean(name = "sqlSessionTemplate2")
    public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactoryDev02()); // 使用上面配置的Factory
        return template;
    }

}
