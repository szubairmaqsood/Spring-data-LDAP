package com.example.Java.Brains.LDAP.Security

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.LdapShaPasswordEncoder

@EnableWebSecurity
class SecurityConfigurtations:WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.ldapAuthentication()
                ?.userDnPatterns("uid={0},ou=people")
                ?.groupSearchBase("ou=groups")
                ?.contextSource()
                ?.url("ldap://localhost:8389/dc=springframework,dc=org")
                ?.and()
                ?.passwordCompare()
                ?.passwordEncoder(LdapShaPasswordEncoder())
                ?.passwordAttribute("userPassword")
    }



    override fun configure(http: HttpSecurity?) {
        http
                ?.authorizeRequests()
                ?.anyRequest()?.fullyAuthenticated()
                ?.and()
                ?.formLogin()
    }
}