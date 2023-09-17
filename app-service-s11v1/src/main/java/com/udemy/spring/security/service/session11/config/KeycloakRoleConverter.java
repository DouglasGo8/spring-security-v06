package com.udemy.spring.security.service.session11.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
  @Override
  @SuppressWarnings("unchecked")
  public Collection<GrantedAuthority> convert(Jwt jwt) {
    var realms = (Map<String, List<String>>) jwt.getClaims().get("realm_access");
    if (realms == null || realms.isEmpty())
      return List.of();
    //
    return realms.get("roles")
            .stream()
            .map(r -> "ROLE_" + r)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

  }

}
