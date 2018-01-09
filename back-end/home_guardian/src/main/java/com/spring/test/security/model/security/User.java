package com.spring.test.security.model.security;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;









import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.test.entities.PersonneAutorisee;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
//    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    @Column(name = "NOM", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String nom;
    
    @Column(name = "PRENOM", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String prenom;
    

    @Column(name = "EMAIL", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String email;

    @Column(name = "ENABLED")
    @NotNull
    private int enabled;

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;
    
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PersonneAutorisee> personneAutorisees;
    

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private List<Authority> authorities;

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public User() {
		super();
		this.enabled = 0 ;
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String nom, String prenom, String email,
			int enabled, Date lastPasswordResetDate) {
		super();
		this.username = username;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
    
    
 // user_personnes
    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public void setPersonnes(Set<PersonneAutorisee> personneAutorisees){
    	this.personneAutorisees = personneAutorisees ;
    }
    
    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<PersonneAutorisee> getPersonnes(){
    	return this.personneAutorisees;
    }
//    public String toString(){
//    	String info = "";
//        JSONObject jsonInfo = new JSONObject();
//        jsonInfo.put("name",this.username);
//        
//        JSONArray personneArray = new JSONArray();
//        if(this.personneAutorisees != null){
//            this.personneAutorisees.forEach(personneAutorise->{
//                JSONObject subJson = new JSONObject();
//                subJson.put("name", personneAutorise.getName());
//                personneArray.put(subJson);
//            });
//        }
//        jsonInfo.put("personnesAutorisees", personneArray);
//        info = jsonInfo.toString();
//        return info;
//    }
}