package com.spring.test.RestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.test.DAO.ProduitRepository;
import com.spring.test.entities.Produit;

@RestController
public class ProduitRestService {
	@Autowired
	private ProduitRepository produitRepository ;
	
//	@RequestMapping(value="/produits" , method = RequestMethod.GET)
//	public List<Produit> listProduit() {
//		return produitRepository.findAll(); 
//	}
	@RequestMapping(value="/produits" , method = RequestMethod.GET)
	public Page<Produit> listProduit(int page, int size) {
		return produitRepository.findAll(new PageRequest(page, size)); 
	}
	
	@RequestMapping(value="/chercherProduits" , method = RequestMethod.GET)
	public Page<Produit> chercher(
			String mc,
			@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="5") int size) {
		return produitRepository.chercherProduit("%"+mc+"%",new PageRequest(page, size)); 
	}
	
	
	@RequestMapping(value="/produits/{id}" , method = RequestMethod.GET)
	public Produit getProduit(@PathVariable("id") Long id) {
		return produitRepository.findOne(id); 
	}
	
//	@RequestMapping(value="/produits" , method = RequestMethod.POST)
//	public Produit addProduit(@RequestBody Produit p) {
//		return produitRepository.save(p); 
//	}
	
	@RequestMapping(value="/produits/{id}" , method = RequestMethod.PUT)
	public Produit updateProduit(@PathVariable("id") Long id, @RequestBody Produit p) {
		p.setId(id);
		return produitRepository.saveAndFlush(p); 
	}
	
	@RequestMapping(value="/produits/{id}" , method = RequestMethod.DELETE)
	public void deleteProduit(@PathVariable("id") Long id ) {
		
		produitRepository.delete(id); 
	}
}
