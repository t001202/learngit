package com.example;

import lombok.extern.java.Log;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import com.github.pagehelper.PageHelper;

import java.util.Properties;

/**
 * Created by WangYx on 2017/9/17.
 */
@Configuration
@EnableTransactionManagement
@Log
public class MybatisConfiguration extends TransactionAutoConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactoryBean")
    public SqlSessionFactory getSqlSessionFactoryBean(){

        try {
            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource);
            // 手写配置
            // 配置类型别名
            sessionFactoryBean.setTypeAliasesPackage("com.example.domain");

            // 配置mapper的扫描，找到所有的mapper.xml映射文件
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources("classpath:com/example/mapper/*.xml");
            sessionFactoryBean.setMapperLocations(resources);

            // 加载全局的配置文件
//            sessionFactoryBean.setConfigLocation(
//                    new DefaultResourceLoader().getResource("classpath:application-dev.properties"));

            //添加插件
            sessionFactoryBean.setPlugins(new Interceptor[]{pageHelper()});
            return sessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean
    public PageHelper pageHelper(){
        log.info("MyBatis分页插件PageHelper");
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

}
