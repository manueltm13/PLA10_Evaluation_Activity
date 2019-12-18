package ga.manuelgarciacr.pla10.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import ga.manuelgarciacr.pla10.validators.DateValidation;
import ga.manuelgarciacr.pla10.validators.EmailValidation;

@Entity
@Table(name = "users")
@org.hibernate.annotations.Table(appliesTo="tbl_users", optional=false) 
@SecondaryTable(name = "tbl_users", pkJoinColumns = @PrimaryKeyJoinColumn(name = "userCode", referencedColumnName = "username")
, uniqueConstraints = @UniqueConstraint(name = "email_user_uc", columnNames = "userEmail"))
public class User {
	@Id
	@Column(name = "username")
	@Size(min = 3, message = "Minimum three characters")
	private String username;
	@Column(name = "password", nullable = false)
	@Size(max = 250, message="Maximum 250 characters")
	private String password = "";
	@Column(name = "enabled", nullable = false)
	@NotNull(message="Not null")
	private boolean enabled;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authority> authorities = new HashSet<>();
	// Secondary table
	@Column(table = "tbl_users", unique = true)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull(message="Empty field")
	private Date userPwdDate;
	@Column(table = "tbl_users")
	@EmailValidation
	private String userEmail;
	@Transient
	private String rols;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password != null && !password.equals(""))
			this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public Date getUserPwdDate() {
		return userPwdDate;
	}

	public void setUserPwdDate(Date userPwdDate) {
		this.userPwdDate = userPwdDate;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getRols() {
		return rols;
	}

	public void setRols(String rols) {
		this.rols = rols;
	}
	
}