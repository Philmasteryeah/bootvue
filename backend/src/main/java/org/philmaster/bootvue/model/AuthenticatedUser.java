package org.philmaster.bootvue.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AuthenticatedUser extends User {

	private static final long serialVersionUID = 1L;

	private Account account;

	public AuthenticatedUser(Account account) {
		this(account.getUsername(), account.getPassword(), account.getAuthorities());
		this.account = account;
	}

	private AuthenticatedUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public Long getId() {
		return account.getId();
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
