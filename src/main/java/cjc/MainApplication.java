package cjc;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableTransactionManagement
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@MapperScan("cjc.mapper")
public class MainApplication extends WebMvcConfigurerAdapter{

	public static final String CONFIG_ENV = "CONFIG_ENV";
	
    @Resource(name="txManager")
    private PlatformTransactionManager txManager;
	
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
	
	
//	  @Override
//	  public void addViewControllers(ViewControllerRegistry registry) {
//	    registry.addViewController("/home").setViewName("home");
//	    registry.addViewController("/").setViewName("home");
//	    registry.addViewController("/profile").setViewName("profile");
//	  }
//	

    // 创建事务管理器2
    @Bean(name = "txManager")
    public PlatformTransactionManager txManager(EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

    // 实现接口 TransactionManagementConfigurer 方法，其返回值代表在拥有多个事务管理器的情况下默认使用的事务管理器
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return txManager;
    }
	
	
 	@Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
// 	//显示声明CommonsMultipartResolver为mutipartResolver
//    @Bean(name = "multipartResolver")
//       public MultipartResolver multipartResolver(){
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setDefaultEncoding("UTF-8");
//        resolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
//        resolver.setMaxInMemorySize(40960);
//        resolver.setMaxUploadSize(50*1024*1024);//上传文件大小 50M 50*1024*1024
//        return resolver;
//    }   
// 	
 	
	public static void main(String[] args) throws Exception {
	SpringApplication.run(MainApplication.class, args);
	   
	}

}
