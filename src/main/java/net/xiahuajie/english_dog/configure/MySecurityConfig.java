package net.xiahuajie.english_dog.configure;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.xiahuajie.english_dog.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import com.alibaba.fastjson.JSONObject;

import net.xiahuajie.english_dog.controller.entity.ResponseData;

/**
 * Security配置
 * 
 * @author xiahuajie
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 依赖注入自定义的登录成功处理器
	 */
	@Resource(name = "furyAuthSuccessHandler")
	private FuryAuthSuccessHandler furyAuthSuccessHandler;
	/**
	 * 依赖注入自定义的登录失败处理器
	 */
	@Resource(name = "furyAuthFailureHandler")
	private FuryAuthFailureHandler furyAuthFailureHandler;
	/**
	 * 依赖注入自定义的注销成功的处理器
	 */
	@Resource(name = "myLogoutSuccessHandler")
	private MyLogoutSuccessHandler myLogoutSuccessHandler;

	/**
	 * 注册没有权限的处理器
	 */
	@Resource(name = "restAuthAccessDeniedHandler")
	private RestAuthAccessDeniedHandler restAuthAccessDeniedHandler;

	@Resource(name = "myAuthenticationEntryPoint")
	private AuthenticationEntryPoint authenticationEntryPoint;

	/*** 注入自定义的CustomPermissionEvaluator */
	@Bean
	public DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler() {
		DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
		handler.setPermissionEvaluator(new CustomPermissionEvaluator());
		return handler;
	}

	@Bean(name = "bCryptPasswordEncoder")
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*** 注入我们自己的登录逻辑验证器AuthenticationProvider */
	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 这里可启用我们自己的登陆验证逻辑
		auth.authenticationProvider(authenticationProvider);
	}

	/**
	 * 配置spring security的控制逻辑
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().authenticationEntryPoint((request, response, authException) -> {
			response.setContentType("application/json;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.getWriter().write(JSONObject.toJSONString(ResponseData.unauth()));
		}).and().authorizeRequests()
				// "/login"不进行权限验证
				.antMatchers("/login").permitAll().antMatchers("/favicon.ico").permitAll().anyRequest().authenticated() // 其他的需要登陆后才能访问
				.and().formLogin()
				// loginProcessingUrl用于指定前后端分离的时候调用后台登录接口的名称
				.loginProcessingUrl("/login")
				// 配置登录成功的自定义处理类
				.successHandler(furyAuthSuccessHandler)
				// 配置登录失败的自定义处理类
				.failureHandler(furyAuthFailureHandler).and()
				// loginProcessingUrl用于指定前后端分离的时候调用后台注销接口的名称
				.logout().logoutUrl("/logout").logoutSuccessHandler(myLogoutSuccessHandler).and()
				// 配置没有权限的自定义处理类
				.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(restAuthAccessDeniedHandler).and().cors()// 新加入
				.and().csrf().disable();// 取消跨站请求伪造防护
	}

}
