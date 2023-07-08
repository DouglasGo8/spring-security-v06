package com.udemy.spring.security.service.session06.controller;

import com.udemy.spring.security.service.session06.model.Notice;
import com.udemy.spring.security.service.session06.repo.NoticeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
public class NoticesController {

  final NoticeRepository noticeRepository;

  @GetMapping("/notices")
  public ResponseEntity<List<Notice>> getNotices() {
    List<Notice> notices = this.noticeRepository.findAllActiveNotices();
    if (notices != null) {
      return ResponseEntity.ok()
              .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
              .body(notices);
    } else {
      return null;
    }
  }

}
