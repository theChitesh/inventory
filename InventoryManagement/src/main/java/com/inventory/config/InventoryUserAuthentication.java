package com.inventory.config;

import java.util.Collection;

import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class InventoryUserAuthentication implements Authentication {

  private final InventoryUser inventoryUser;
  private boolean authenticated = false;

  public InventoryUser getInventoryUser() {
    return inventoryUser;
  }

  public SignedJWT getJwt() {
    return jwt;
  }

  private final SignedJWT jwt;

  public InventoryUserAuthentication(final String userName, final String userId,
                                     final Collection<GrantedAuthority> roles, final SignedJWT jwt) {
    this.jwt = jwt;
    this.inventoryUser = new InventoryUser(userName, userId, roles);
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return inventoryUser.getAuthorities();
  }

  @Override
  public Object getCredentials() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object getDetails() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object getPrincipal() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isAuthenticated() {
    System.out.println("in isAUthenticated");
    return authenticated;
  }

  @Override
  public void setAuthenticated(boolean arg0) throws IllegalArgumentException {
    authenticated = arg0;
  }

}
