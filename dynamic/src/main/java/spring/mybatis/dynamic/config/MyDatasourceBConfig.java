package spring.mybatis.dynamic.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by WangYx on 2018/4/14.
 */
@Configuration
@MapperScan(basePackages = "spring.mybatis.dynamic.mapperb",
sqlSessionTemplateRef = "sqlSessionTemplatementB")
public class MyDatasourceBConfig {


    @Bean(name = "dataSourceB")
    @ConfigurationProperties(prefix = "spring.datasource.test2")
    public DataSource createDataSource()throws Exception{
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "sqlSessionFactoryB")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("dataSourceB") DataSource dataSource)throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().
                        getResources("classpath:mybatis/mapperb/*.xml")
        );

        return bean.getObject();

    }
    @Bean(name = "sqlSessionTransactionB")
    public DataSourceTransactionManager  createTransactionManagerA(@Qualifier("dataSourceB") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean(name = "sqlSessionTemplatementB")
    public SqlSessionTemplate createSqlSessionTempalte(@Qualifier("sqlSessionFactoryB") SqlSessionFactory sqlSessionFactory)throws
        Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }



}
