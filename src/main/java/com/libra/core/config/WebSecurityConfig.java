package com.libra.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.libra.securities.UserDetailService;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(userDetailsService());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // là kĩ thuật tấn công bằng cách sử dụng quyền chứng thực của người sử dụng đối
		// với 1 website khác

		// Các trang không yêu cầu login như vậy ai cũng có thể vào được admin hay user
		// hoặc guest có thể vào các trang
		http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();

		// Trang /user yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
		// Nếu chưa login, nó sẽ redirect tới trang /login.sau Mình dung hasAnyRole để
		// cho phép ai được quyền vào
		http.authorizeRequests().antMatchers("/user").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

		// Trang chỉ dành cho ADMIN
		http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
		 // Khi ng dung cố tình vào trang admin
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        // Cấu hình cho Login Form.
		http.authorizeRequests().and().formLogin()//
		// Submit URL của trang login
				.loginProcessingUrl("/check_login") // action của nó là j_spring_security_check
				.loginPage("/login")//
				.defaultSuccessUrl("/userAccountInfo")// đây Khi đăng nhập thành công thì vào trang này. userAccountInfo
				// sẽ được khai báo trong controller để hiển thị trang view
				// tương ứng
				.failureUrl("/login?error=true")// Khi đăng nhập sai username và password thì nhập lại
				.usernameParameter("username")// tham số này nhận từ form login input name='username'
				.passwordParameter("password")// tham số này nhận từ form login input name='password'
				// Cấu hình cho Logout Page. Khi logout mình trả về trang
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
	   
	}
}
