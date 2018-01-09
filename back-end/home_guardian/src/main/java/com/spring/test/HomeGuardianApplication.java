package com.spring.test;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.test.DAO.PersonneAutoriseeRepository;
import com.spring.test.DAO.ProduitRepository;
import com.spring.test.entities.PersonneAutorisee;
import com.spring.test.entities.Produit;
import com.spring.test.security.model.security.User;
import com.spring.test.security.security.repository.UserRepository;

@SpringBootApplication
public class HomeGuardianApplication implements CommandLineRunner {
	@Autowired
	private ProduitRepository produitRepository;
	
	@Autowired
	private PersonneAutoriseeRepository personneAutoriseeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(HomeGuardianApplication.class, args);
		
		
	}

	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		
		produitRepository.save(new Produit("LX 534" , 900 , 23));
		produitRepository.save(new Produit("HX 655" , 350 , 2));
		produitRepository.save(new Produit("PG 301" , 1200 , 20));
		
//		personneAutoriseeRepository.save(new PersonneAutorisee("Slim","Tebourski",));
//		personneAutoriseeRepository.save(new PersonneAutorisee("Selena","Gomez"));
//		personneAutoriseeRepository.save(new PersonneAutorisee("Suprano","Peres"));
//		personneAutoriseeRepository.save(new PersonneAutorisee("Gonzales","Enrique"));
		
		
		
		
//		heroRepository.save(new Hero("Batman"));
//		heroRepository.save(new Hero("SuperMan"));
//		heroRepository.save(new Hero("Naruto"));
		Date date = new Date();
//		userRepository.save(new User("admin",
//				"$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi",
//				"prenom_admin",
//				"admin@gmail.com",
//				1,
//				date));
//		userRepository.save(new User("cristal",
//				"cristal",
//				"cristal",
//				"cristal@gmail.com",
//				1,
//				date));
//		userRepository.save(new User("okkkk",
//				"okkkk",
//				"okkkk",
//				"okkkk@gmail.com",
//				1,
//				date));
		final User adminUser = new User("admin",
				"$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi",
				"nom_admin",
				"prenom_admin",
				"admin@gmail.com",
				1,
				date);
		
		Set paAs = new HashSet<PersonneAutorisee>(){{
            add(new PersonneAutorisee("nom1","prenom1", adminUser));
            add(new PersonneAutorisee("nom2","prenom2", adminUser));
            add(new PersonneAutorisee("nom3","prenom3", adminUser));
        }};
        adminUser.setPersonnes(paAs);

        final User slimUser = new User("slim",
				"$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi",
				"nom_slim",
				"prenom_slim",
				"slim.slim@gmail.com",
				1,
				date);
        Set<PersonneAutorisee> paBs = new HashSet();
        paBs.add(new PersonneAutorisee("s1","pres1", slimUser));
        paBs.add(new PersonneAutorisee("s2","pres2", slimUser));
        paBs.add(new PersonneAutorisee("s3","pres3", slimUser));
       

        slimUser.setPersonnes(paBs);

        userRepository.save(new HashSet<User>() {{
            add(adminUser);
            add(slimUser);
        }});



		
//		List<Hero> heroes = (List<Hero>) heroRepository.findAll();
//		for(Hero h : heroes){
//			System.out.println(h.getName());
//		}
		
		List<User> users = (List<User>) userRepository.findAll();
		for(User u : users){
			System.out.println(u.getUsername());
			System.out.println(u.getUsername());
			System.out.println(u.getNom());
			System.out.println(u);
			System.out.println(userRepository.findByUsername("admin"));
			System.out.println(userRepository.findByUsername("admin"));
			
			
			
			
			Set<PersonneAutorisee> hs = u.getPersonnes();
		   
			Iterator iter = hs.iterator();
			while (iter.hasNext()) {
			    System.out.println(iter.next());
			   
			    
			}
		                
		    
			
		}
		
		
//		List<Produit> prods = produitRepository.findAll();
//		for(Produit p : prods){
//			System.out.println(p.getDesignation());
//		}
//		
//		List<PersonneAutorisee> personnes = (List<PersonneAutorisee>) personneAutoriseeRepository.findAll();
//		for(PersonneAutorisee p : personnes){
//			System.out.println(p.getPrenom()+' '+p.getNom());
//		}
		
	}
}

