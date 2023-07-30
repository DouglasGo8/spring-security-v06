package com.udemy.spring.security.service.session09.repo;

import com.udemy.spring.security.service.session09.model.ContactMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<ContactMessage, String> {
}
