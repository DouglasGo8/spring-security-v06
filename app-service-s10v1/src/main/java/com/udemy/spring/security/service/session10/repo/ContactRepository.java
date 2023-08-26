package com.udemy.spring.security.service.session10.repo;

import com.udemy.spring.security.service.session10.model.ContactMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<ContactMessage, String> {
}
