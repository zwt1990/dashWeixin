package cjc;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@MapperScan("cjc.mapper")
@ServletComponentScan
public class MainApplication extends SpringBootServletInitializer {

	public static final String CONFIG_ENV = "CONFIG_ENV";
	
//    @Resource(name="txManager")
//    private PlatformTransactionManager txManager;
	
	@Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

	@Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }
	
//	 @Bean(name = "txManager")
//    public PlatformTransactionManager txManager(EntityManagerFactory factory) {
//        return new JpaTransactionManager(factory);
//    }
//	@Override
//	public PlatformTransactionManager annotationDrivenTransactionManager() {
//		return txManager;
//	}
 	
     protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
         return application.sources(MainApplication.class);
     }
 	
	public static void main(String[] args) throws Exception {
	SpringApplication.run(MainApplication.class, args);
	   
	}

	

}
