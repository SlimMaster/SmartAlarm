package com.spring.test.security.security;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtUser implements UserDetails {

    private final Long id;
    private final String username;
    private final String nom;
    private final String prenom;
    private final String password;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private final int enabled;
    private final Date lastPasswordResetDate;

    public JwtUser(
          Long id,
          String username,
          String nom,
          String prenom,
          String email,
          String password, Collection<? extends GrantedAuthority> authorities,
          int enabled,
          Date lastPasswordResetDate
    ) {
        this.id = id;
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

//    @Override
//    public String getUsername() {
//        return username;
//    }
    public String getUsername() {
    	// TODO Auto-generated method stub
    	return username;
    }
//
//    @JsonIgnore
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
    @JsonIgnore
    public boolean isAccountNonExpired() {
    	// TODO Auto-generated method stub
    	return true;
    }
    
//    @JsonIgnore
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
    
    @JsonIgnore
    public boolean isAccountNonLocked() {
    	// TODO Auto-generated method stub
    	return true;
    }
  
//
//    @JsonIgnore
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
    
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
    	// TODO Auto-generated method stub
    	return true;
    }
    
 
    
    
    public String getNom() {
        return nom;
    }
    

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

//    @JsonIgnore
//    @Override
//    public String getPassword() {
//        return password;
//    }
    @JsonIgnore
    public String getPassword() {
    	// TODO Auto-generated method stub
    	return password;
    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	// TODO Auto-generated method stub
    	return authorities;
    }
//
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }
    
    
    
    
    
    

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		boolean isEnabled;
		if (enabled == 1) isEnabled = true;
		else isEnabled = false;
		return isEnabled;
	}












}
