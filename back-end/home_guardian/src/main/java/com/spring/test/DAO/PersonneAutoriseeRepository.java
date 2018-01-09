package com.spring.test.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.test.entities.PersonneAutorisee;

public interface PersonneAutoriseeRepository extends JpaRepository<PersonneAutorisee, Long>{
	@Query("select p from PersonneAutorisee p where p.nom like :x")
	public Page<PersonneAutorisee> chercherPersonne(@Param("x") String mc ,Pageable pageable);
	
//	@Query("select p from PersonneAutorisee p where p.user_id like :x")
//	public Page<PersonneAutorisee> getAllPersonnes(@Param("x") String mc ,Pageable pageable);

}
