package com.udemy.spring.security.service.session11.controller;

import com.udemy.spring.security.service.session11.model.ContactMessage;
import com.udemy.spring.security.service.session11.repo.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Random;

@RestController
@AllArgsConstructor
public class ContactController {

  final ContactRepository contactRepository;

  @PostMapping("/contact")
  //@PreFilter("filterObject.contactName != 'Test'")
  @PostFilter("filterObject.contactName != 'Test'")
  public ContactMessage saveContactInquiryDetails(@RequestBody ContactMessage contact) {
    contact.setContactId(getServiceReqNumber());
    contact.setCreateDt(new Date(System.currentTimeMillis()));
    return this.contactRepository.save(contact);
  }

  public String getServiceReqNumber() {
    var random = new Random();
    int ranNum = random.nextInt(999999999 - 9999) + 9999;
    return "SR"+ranNum;
  }
}
