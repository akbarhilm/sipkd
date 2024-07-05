package spp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import spp.services.UserNamePassAuthProvider;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.authenticationProvider(userNamePassAuthProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/static/**", "/logout", "/login", "/aboutpage").permitAll()
                .anyRequest().hasAnyRole("6")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll().defaultSuccessUrl("/beranda", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        http.sessionManagement().sessionFixation().none()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true).sessionRegistry(sessionRegistry()).
                and().invalidSessionUrl("/login")
                .sessionAuthenticationErrorUrl("/login");

    }

    @Bean
    public UserNamePassAuthProvider userNamePassAuthProvider() {
        return new UserNamePassAuthProvider();
    }
    /* private static final Properties properties = new Properties();

     static {
     try {
     properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("sso.properties"));
     } catch (IOException ex) {
     ex.printStackTrace();
     }
     }

     @Bean
     public SessionRegistry sessionRegistry() {
     return new SessionRegistryImpl();
     }

     @Override
     protected void configure(HttpSecurity http) throws Exception { 
     http
     .exceptionHandling()
     .authenticationEntryPoint(casAuthenticationEntryPoint()).and().csrf().disable()
     .addFilter(casAuthenticationFilter()).authorizeRequests()
     .antMatchers("/static/**", "/logout", "/login", "/aboutpage").permitAll()
     .anyRequest().hasAnyRole("6").and()
     .logout()
     .logoutUrl("/logout")
     .logoutSuccessUrl(properties.getProperty("sso.logout"))
     .invalidateHttpSession(true)
     .deleteCookies("JSESSIONID").and().sessionManagement().invalidSessionUrl(properties.getProperty("sso.logout"))
     .sessionAuthenticationErrorUrl(properties.getProperty("sso.logout")); 
        
        

     }

     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth
     .authenticationProvider(casAuthenticationProvider());
     }
    

     @Bean
     public UserNamePassAuthProvider userNamePassAuthProvider() {
     return new UserNamePassAuthProvider();
     }

     @Bean
     public ServiceProperties serviceProperties() {
     ServiceProperties serviceProperties = new ServiceProperties();
     serviceProperties.setService(properties.getProperty("sso.service"));
     serviceProperties.setSendRenew(false);
     return serviceProperties;
     }

     @Bean
     public CasAuthenticationProvider casAuthenticationProvider() {
     CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
     casAuthenticationProvider.setAuthenticationUserDetailsService(authenticationUserDetailsService());
     casAuthenticationProvider.setServiceProperties(serviceProperties());
     casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
     casAuthenticationProvider.setKey("an_id_for_this_auth_provider_only");
     return casAuthenticationProvider;
     }

     @Bean
     public AuthenticationUserDetailsService authenticationUserDetailsService() {
     return new SipkdCasAuthenticationUserDetailsService();
     }

     @Bean
     public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
     return new Cas20ServiceTicketValidator(properties.getProperty("sso.cas"));
     }

     @Bean
     public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
     CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
     casAuthenticationFilter.setAuthenticationManager(authenticationManager());
     AuthenticationSuccessHandler sukses = new SimpleUrlAuthenticationSuccessHandler("/beranda");
     casAuthenticationFilter.setAuthenticationSuccessHandler(sukses);
     AuthenticationFailureHandler gagal = new SimpleUrlAuthenticationFailureHandler("/logout");
     casAuthenticationFilter.setAuthenticationFailureHandler(gagal);
     return casAuthenticationFilter;
     }

     @Bean
     public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
     CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
     casAuthenticationEntryPoint.setLoginUrl(properties.getProperty("sso.login"));
     casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
     return casAuthenticationEntryPoint;
     }*/
}
