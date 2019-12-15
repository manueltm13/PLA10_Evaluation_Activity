package ga.manuelgarciacr.pla10.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/*
 * Aquí he intentado usar el mayor número posible de anotaciones
 */
@Entity
@Table(name = "tbl_centers",
	catalog = "tm13pla10",
	uniqueConstraints = { @UniqueConstraint(columnNames = "cenName") })
public class Center {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cenCode", updatable = false, nullable = false)
	private int cenCode;
	@Column(name = "cenName", unique = true, length = 200, nullable = false)
	private String cenName;
	@Column(name = "cenAdd", length = 200, nullable = false)
	private String cenAdd;
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }) // Avoid CascadeType.REMOVE in ManyToMany
	@JoinTable(name = "tbl_centersdepartments", 
		joinColumns = @JoinColumn(name = "cenCode", nullable = false, updatable = false), 
		inverseJoinColumns = @JoinColumn(name = "depCode", nullable = false, updatable = false))
	private Set<Department> departments = new HashSet<>();

	// Methods
	
	public void addDepartment(Department dep) {
        departments.add(dep);
        dep.getCenters().add(this);
    }
 
    public void removeDepartment(Department dep) {
        departments.remove(dep);
        dep.getCenters().remove(this);
    }
	
	// Getters and Setters
	
	public int getCenCode() {
		return cenCode;
	}

	public void setCenCode(int cenCode) {
		this.cenCode = cenCode;
	}

	public String getCenName() {
		return cenName;
	}

	public void setCenName(String cenName) {
		this.cenName = cenName;
	}

	public String getCenAdd() {
		return cenAdd;
	}

	public void setCenAdd(String cenAdd) {
		this.cenAdd = cenAdd;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

}
