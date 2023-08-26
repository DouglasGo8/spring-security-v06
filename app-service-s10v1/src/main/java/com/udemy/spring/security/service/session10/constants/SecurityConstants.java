package com.udemy.spring.security.service.session10.constants;

public interface SecurityConstants {

  // Only known by backend app, in production env, must be injected by environment variable (CICD etc)
  String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
  String JWT_HEADER = "Authorization";
}
