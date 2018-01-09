package com.spring.test.RestService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javassist.bytecode.Descriptor.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.test.DAO.PersonneAutoriseeRepository;
import com.spring.test.entities.PersonneAutorisee;
import com.spring.test.entities.Produit;
import com.spring.test.security.model.security.User;
import com.spring.test.security.security.repository.UserRepository;

@RestController
public class PersonneAutoriseeRestService {
	
	@Autowired
	private PersonneAutoriseeRepository personneAutoriseeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/personne_autorisee" , method = RequestMethod.GET)
	public List<PersonneAutorisee> listPersonneAutorisees() {
		return personneAutoriseeRepository.findAll(); 
	}
	
	@RequestMapping(value="/chercherPersonne" , method = RequestMethod.GET)
	public Page<PersonneAutorisee> chercher(
			String mc,
			@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="5") int size) {
		return personneAutoriseeRepository.chercherPersonne("%"+mc+"%",new PageRequest(page, size)); 
	}
	
	@RequestMapping(value="/personne_autorisee/{id}" , method = RequestMethod.GET)
	public PersonneAutorisee getPersonneAutorisee(@PathVariable("id") Long id) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = authentication.getName();
//		
//		System.out.println("current User is : "+currentPrincipalName);
//		User u = userRepository.findByUsername(currentPrincipalName);
//		Set set = u.getPersonnes();
//		Iterator iter = (Iterator) set.iterator();
//		while (iter.hasNext()) {
//		    if ( iter.equals( personneAutoriseeRepository.findOne(id)   )  ) {
//		    	System.out.println(iter);
//		    	System.out.println(iter.next());
//		    	
//		    	return personneAutoriseeRepository.findOne(id);
//		}
//		}
		return personneAutoriseeRepository.findOne(id);
	}
	
	@RequestMapping(value="/personne_autorisee" , method = RequestMethod.POST)
	public PersonneAutorisee addPersonneAutorisee(@RequestBody PersonneAutorisee pa) {
		System.out.println("salut");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		
		User u = userRepository.findByUsername(currentPrincipalName);
		
		PersonneAutorisee newPers = new PersonneAutorisee(pa.getNom(),pa.getPrenom(),u);
		
		
		u.getPersonnes().add(newPers);
		
		return personneAutoriseeRepository.save(newPers); 
	}
	
	@RequestMapping(value="/personne_autorisee/{id}" , method = RequestMethod.PUT)
	public PersonneAutorisee updatePersonneAutorisee(@PathVariable("id") Long id, @RequestBody PersonneAutorisee p) {
		p.setId(id);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		
		User u = userRepository.findByUsername(currentPrincipalName);
		p.setUser(u);
		return personneAutoriseeRepository.saveAndFlush(p); 
	}
	
	@RequestMapping(value="/personne_autorisee/{id}" , method = RequestMethod.DELETE)
	public void deletePersonneAutorisee(@PathVariable("id") Long id ) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		System.out.println("current User is : "+currentPrincipalName);
		User u = userRepository.findByUsername(currentPrincipalName);
		u.getPersonnes().remove(personneAutoriseeRepository.findOne(id));
	    personneAutoriseeRepository.delete(id); 
		//return "Vous venez de supprimer cette personne avec succès";
	}

}
