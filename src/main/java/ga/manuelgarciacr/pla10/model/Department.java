package ga.manuelgarciacr.pla10.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/*
 * Aquí he intentado usar el menor número posible de anotaciones
 */

@Entity
@Table(name = "tbl_departments")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int depCode;
	private String depName;
	@ManyToMany(mappedBy = "departments")
	private Set<Center> centers = new HashSet<>();
	
	// Getters and Setters
	
	public int getDepCod() {
		return depCode;
	}
	
	public void setDepCod(int depCod) {
		this.depCode = depCod;
	}
	
	public String getDepName() {
		return depName;
	}
	
	public void setDepName(String depName) {
		this.depName = depName;
	}

	public Set<Center> getCenters() {
		return centers;
	}

	public void setCenters(Set<Center> centers) {
		this.centers = centers;
	}

}
