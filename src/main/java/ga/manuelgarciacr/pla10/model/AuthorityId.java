package ga.manuelgarciacr.pla10.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

public class AuthorityId implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -4290686176000380016L;

	@Column
    private String authority;
 
    @Column
    private String username;
 
    public AuthorityId() {
    }
 
    public AuthorityId(String authority, String username) {
        this.authority = authority;
        this.username = username;
    }
 
    public String getAuthority() {
        return authority;
    }
 
    public void setAuthority(String authority) {
        this.authority = authority;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) 
        	return true;
        if (!(o instanceof AuthorityId)) 
        	return false;
        AuthorityId that = (AuthorityId) o;
        return Objects.equals(getAuthority(), that.getAuthority()) &&
                Objects.equals(getUsername(), that.getUsername());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getAuthority(), getUsername());
    }
}