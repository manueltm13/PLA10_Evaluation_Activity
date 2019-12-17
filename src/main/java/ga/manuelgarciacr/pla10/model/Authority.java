package ga.manuelgarciacr.pla10.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ga.manuelgarciacr.pla10.model.dao.AuthorityDAO;

@Entity
@Table(name = "authorities",
uniqueConstraints = @UniqueConstraint(
		columnNames = { "authority", "username" }))
//@IdClass(AuthorityId.class)
public class Authority implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "authority")
	private String authority;
	@Id
	@ManyToOne
	@JoinColumn(name = "username")
	private User user;
	
	public Authority() {
		super();
	}
	
	public Authority(String authority, User user) {
		super();
		this.authority = authority;
		this.user = user;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}