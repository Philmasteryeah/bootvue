package org.philmaster.bootvue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

/**
 * An authority (a security role) used by Security.
 */
@Entity
@Table(name = "authority")
public class Authority extends BaseModel implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	// @NotNull
	@Size(min = 0, max = 50)
	@Column(name = "name", length = 50)
	private String name;

	public Authority() {
	}

	@Override
	public String getAuthority() {
		return this.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
