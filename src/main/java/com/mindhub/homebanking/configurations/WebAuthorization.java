package com.mindhub.homebanking.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization extends WebSecurityConfigurerAdapter {


    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        //Si create es falso y la solicitud no tiene HttpSession válida, este método devuelve nulo.
        //usa logica booleana para explicar.
        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }
    }



    @Override

    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()



                .antMatchers("/rest/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST,"/api/createLoans").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST,"/api/clients").permitAll()

                .antMatchers(HttpMethod.POST,"/api/login").hasAuthority("CLIENT")

                .antMatchers(HttpMethod.GET,"/api/loans").hasAnyAuthority("CLIENT","ADMIN")

                .antMatchers("/web/transfers.html").hasAuthority("CLIENT")

                .antMatchers("/web/accounts.html").hasAuthority("CLIENT")

                .antMatchers("/web/account.html").hasAuthority("CLIENT")

                .antMatchers("/web/account.html").hasAuthority("CLIENT")

                .antMatchers("/web/newLoan.html").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST,"/api/login").hasAuthority("CLIENT")

                .antMatchers(HttpMethod.POST,"/api/clients/current/cards").hasAuthority("CLIENT")

                .antMatchers(HttpMethod.POST,"/api/loans").hasAuthority("CLIENT")

                .antMatchers(HttpMethod.POST,"/clients/current/accounts").hasAuthority("CLIENT")

                .antMatchers(HttpMethod.POST,"/api/transactions").hasAuthority("CLIENT")

                .antMatchers(HttpMethod.POST,"/clients/current/transfer").hasAuthority("CLIENT");



        http.formLogin()

                .usernameParameter("email")

                .passwordParameter("password")

                .loginPage("/api/login");


        http.logout().logoutUrl("/api/logout").deleteCookies("JSESSIONID");

        // desactivar la comprobación de tokens CSRF

        http.csrf().disable();

        // deshabilitar frameOptions para que  no se pueda acceder a h2-console


        http.headers().frameOptions().disable();

        // si el usuario no está autenticado, simplemente envíe una respuesta de falla de autenticación exc==excepsion

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // si el inicio de sesión es exitoso, simplemente borre las banderas que solicitan autenticación



        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        // si falla el inicio de sesión, simplemente envíe una respuesta de falla de autenticación

        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // si el cierre de sesión es exitoso, simplemente envíe una respuesta exitosa

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

    }


}

