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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by WangYx on 2018/4/14.
 */
@Configuration
@MapperScan(value = "spring.mybatis.dynamic.mappera",
            sqlSessionTemplateRef = "sqlSessionTemplatementA")
public class MyDatasourceACofnig {

    @Bean(name = "dataSourceA")
    @ConfigurationProperties(prefix = "spring.datasource.test1")
    @Primary
    public DataSource creatDataSource(){
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "sqlSessionFactoryA")
    @Primary
    public SqlSessionFactory creatSqlSessionFactory(@Qualifier("dataSourceA") DataSource dataSource)throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:mybatis/mappera/*.xml"));
        return bean.getObject();
    }


    @Bean(name = "DataSourceTransactionA")
    @Primary
    public DataSourceTransactionManager createTransactionManager(@Qualifier("dataSourceA") DataSource dataSource)throws Exception{
        return  new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplatementA")
    @Primary
    public SqlSessionTemplate createSqlSessionTemplate(@Qualifier("sqlSessionFactoryA") SqlSessionFactory sessionFactory)throws Exception{
        return  new SqlSessionTemplate(sessionFactory);
    }
}
