package ga.manuelgarciacr.pla10.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
@SecondaryTable(name = "tbl_users", pkJoinColumns = @PrimaryKeyJoinColumn(name = "userCode", referencedColumnName = "username"))
public class User {
	@Id
	@Column(name = "username")
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "enabled", nullable = false)
	private boolean enabled;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authority> authorities = new HashSet<>();
	// Secondary table
	@Column(table = "tbl_users")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date userPwdDate;
	@Column(table = "tbl_users")
	private String userEmail;
	
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

	public void setUsrEmail(String usrEmail) {
		this.userEmail = usrEmail;
	}
	
}