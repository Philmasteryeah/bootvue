package org.philmaster.bootvue.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A user.
 */
@Entity
@Table(name = "user")
//@NamedQuery(name = "findUserByLogin", query = "select u from User u where u.login = :login")
//@NamedQuery(name = "findUserByEmail", query = "select u from User u where u.email = :email")
//@NamedQuery(name = "findUserByResetKey", query = "select u from User u where u.resetKey = :resetKey")
//@NamedQuery(name = "findUserByActivationKey", query = "select u from User u where u.activationKey = :activationKey")
//@NamedQuery(name = "findUserByUserId", query = "select u from User u where u.id = :id")
//@NamedEntityGraph(name = "graph.user.authorities", attributeNodes = @NamedAttributeNode("authorities"))
public class Account extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	// @NotNull

	@Size(max = 50)
	@Column(name = "first_name", length = 50)
	private String firstName;

	@Size(max = 50)
	@Column(name = "last_name", length = 50)
	private String lastName;

	// @Email(regexp = EMAIL_REGEX, message = "bla")

	@Column(name = "email")
	@Email(message = "Please provide a valid Email")
	@NotEmpty(message = "Please provide an email")
	@Size(min = 5, max = 100)
	private String email;

	// @NotNull
	@Column(nullable = false)
	private boolean isActivated = false;

	@Size(min = 2, max = 5)
	@Column(name = "lang_key", length = 5)
	private String langKey;

	@Size(max = 20)
	@Column(name = "activation_key", length = 20)
	private String activationKey;

	@Size(max = 20)
	@Column(name = "reset_key", length = 20)
	private String resetKey;

	@Column(name = "reset_date")
	private Instant resetDate = null;

	@Column(name = "username")
	@NotEmpty(message = "Please provide your username")
	private String username;

	// @Length(min = 5, message = "Your password must have at least 5 characters")

	@Column(name = "password", unique = true)
	@NotEmpty(message = "Please provide your password")
	@JsonIgnore
	private String password;

	@Column(name = "is_enabled")
	@JsonIgnore
	private boolean isEnabled;

	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Calendar createdDate;

	@ManyToMany
	@JoinTable(
	  name = "user_authority", 
	  joinColumns = @JoinColumn(name = "authority_id"), 
	  inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<Authority> authorities;

	// Lowercase the login before saving it in database
	public void setUsername(String username) {
		this.username = username.toLowerCase(Locale.ENGLISH);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setIsActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public String getResetKey() {
		return resetKey;
	}

	public void setResetKey(String resetKey) {
		this.resetKey = resetKey;
	}

	public Instant getResetDate() {
		return resetDate;
	}

	public void setResetDate(Instant resetDate) {
		this.resetDate = resetDate;
	}

	public String getLangKey() {
		return langKey;
	}

	public void setLangKey(String langKey) {
		this.langKey = langKey;
	}

	public Set<Authority> getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public Calendar getCreatedDate() {
		return createdDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

}
