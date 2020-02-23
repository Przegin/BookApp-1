package wiktoria.appdemo.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/mojabaza");
		dataSource.setUsername("root");
		dataSource.setPassword("Pass1123qwer");
		return dataSource;
	}

	@Bean
	public BCryptPasswordEncoder pwdEncrypt(  ) {						//działa w jedną stronę, nie szyfruje tylko haszuje - nie jest to do odszyfrowania
		BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();		//
		return bcp;
	}
}
