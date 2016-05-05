package cjc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("cjc.mybatis.mapper")
public class MainApplication extends SpringBootServletInitializer {

	public static final String CONFIG_ENV = "CONFIG_ENV";
	

	/*@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		
		return application.sources(MainApplication.class).profiles(
				System.getenv(CONFIG_ENV));
	}*/

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MainApplication.class, args);
	}

//	@Bean
//	public ViewResolver getViewResolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setSuffix(".jsp");
//		return resolver;
//	}
}
