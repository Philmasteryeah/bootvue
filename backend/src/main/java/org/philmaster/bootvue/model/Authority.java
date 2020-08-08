package org.philmaster.bootvue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

/**
 * An authority (a security role) used by Security.
 */
@Entity
@Table(name = "authority")
@Data
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	// @NotNull
	@Size(min = 0, max = 50)
	@Id
	@Column(name = "\"name\"", length = 50)
	private String name;

	public Authority() {
	}

	@Override
	public String getAuthority() {
		return this.name;
	}

}
