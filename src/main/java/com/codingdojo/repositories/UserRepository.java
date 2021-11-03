package com.codingdojo.repositories;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.codingdojo.models.User;

public interface UserRepository extends Repository<User, Long> {
    User findByEmail(String email);
    
    User save( User user );
	
	Optional<User> findById( Long id );
    
    
}
