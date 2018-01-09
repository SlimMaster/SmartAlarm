package com.spring.test.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.test.security.model.security.User;

@Entity
public class PersonneAutorisee implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)  //  id généré automatiquement BD
	
	private Long id;
	private String nom;
	private String prenom;
	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
	
	
	public PersonneAutorisee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PersonneAutorisee(String nom, String prenom, User user) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.user = user ;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	// user_personne
    public void setUser(User user){
    	this.user = user;
    }
    
    public User getUser(){
    	return this.user;
    }
    
    public String toString(){
    	String info = "";
    	
        JSONObject jsonInfo = new JSONObject();
        try {
			jsonInfo.put("nom",this.nom);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        JSONObject companyObj = new JSONObject();
        try {
			companyObj.put("username", this.user.getUsername());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			jsonInfo.put("user", companyObj);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        info = jsonInfo.toString();
        return info;
    }
}
