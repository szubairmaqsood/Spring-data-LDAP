package com.example.Java.Brains.LDAP.Security

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.LdapShaPasswordEncoder

@EnableWebSecurity
class SecurityConfigurtations:WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.ldapAuthentication()                                 // Authentication manager is mentioning required ldap configurations
                ?.userDnPatterns("uid={0},ou=people")  //{} it idynamic filled with entry from user
                ?.groupSearchBase("ou=groups")       //it is under group
                ?.contextSource()
                ?.url("ldap://localhost:8389/dc=springframework,dc=org") //following is port and root of ldap
                ?.and()
                ?.passwordCompare()                                        //password compare
                ?.passwordEncoder(LdapShaPasswordEncoder())                //following password encoder
                ?.passwordAttribute("userPassword")         // the property we need
    }



    override fun configure(http: HttpSecurity?) {
        http
                ?.authorizeRequests()
                ?.anyRequest()?.fullyAuthenticated()
                ?.and()
                ?.formLogin()
    }
}