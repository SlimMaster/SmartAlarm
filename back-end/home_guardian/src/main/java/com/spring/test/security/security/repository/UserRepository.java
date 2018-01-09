package com.spring.test.security.security.repository;

import com.spring.test.entities.Produit;
import com.spring.test.security.model.security.User;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestBody;


//@RepositoryRestResource(collectionResourceRel = "users", path = "users")//(exported = false)
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    
//    @Query("select u from User u where p.designation like :x")
	User findByEmail(String mail);
	User findOne(Long id);
	<S extends User> S saveAndFlush(S arg0);
	 
//	List<User> findAll();

//    <S extends User> S save(@RequestBody S arg0);
//	void delete(Long arg0);
//    
//	
//	/*  Ces methodes herités seront sécurisés et non accessibles par des requetes vers l'api 
//	 * 
//	 *  grace a l'annotation (export = false)
//	 * 
//	 * */
//	
//    @RestResource(exported = false)
//    Page<User> findAll(Pageable arg0);
//    
//    @RestResource(exported = false)
//	<S extends User> long count(Example<S> arg0);
//    
//    @RestResource(exported = false)
//	<S extends User> boolean exists(Example<S> arg0);
//    
//    @RestResource(exported = false)
//	<S extends User> Page<S> findAll(Example<S> arg0, Pageable arg1);
//    
//    @RestResource(exported = false)
//	<S extends User> S findOne(Example<S> arg0);
//    
//    @RestResource(exported = false)
//	long count();
//    
//    @RestResource(exported = false)
//	void delete(Iterable<? extends User> arg0);
//    
//    @RestResource(exported = false)
//	void delete(User arg0);
//    
//    @RestResource(exported = false)
//	void deleteAll();
//
//    @RestResource(exported = false)
//	boolean exists(Long arg0);
//    
//    
//	
////    @RestResource(exported = false)
////	void deleteAllInBatch();
////    
////    @RestResource(exported = false)
////	void deleteInBatch(Iterable<User> arg0);
////    
//    
//
//    
//    @RestResource(exported = false)
//	<S extends User> List<S> findAll(Example<S> arg0, Sort arg1);
//    
//    @RestResource(exported = false)
//	<S extends User> List<S> findAll(Example<S> arg0);
//
//    @RestResource(exported = false)
//	List<User> findAll(Iterable<Long> arg0);
//
//    @RestResource(exported = false)
//	List<User> findAll(Sort arg0);
//
////    @RestResource(exported = false)
////	void flush();
//
////    @RestResource(exported = false)
////	User getOne(Long arg0);
//
//    @RestResource(exported = false)
//	<S extends User> List<S> save(Iterable<S> arg0);
//
////    @RestResource(exported = false)
////	<S extends User> S saveAndFlush(S arg0);
//    

}