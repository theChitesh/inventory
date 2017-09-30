package com.inventory.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class InventoryAuthenticationProvider implements AuthenticationProvider {

	
	
	@Override
	public Authentication authenticate(Authentication arg0) throws AuthenticationException {
		System.out.println("in authenticate method");
		return new InventoryUserAuthentication();
	}

	@Override
	public boolean supports(Class<?> arg0) {
		System.out.println("in support ");
		return true;
	}

}
