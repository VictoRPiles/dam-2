package entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Victor Piles
 */
@Entity
@Table(name = "departamentos", schema = "ejemplo", catalog = "")
public class DepartamentosEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "dept_no")
	private int deptNo;
	@Basic
	@Column(name = "dnombre")
	private String dnombre;
	@Basic
	@Column(name = "loc")
	private String loc;
	@OneToMany(mappedBy = "departamentosByDeptNo")
	private Collection<EmpleadosEntity> empleadosByDeptNo;

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDnombre() {
		return dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DepartamentosEntity that = (DepartamentosEntity) o;

		if (deptNo != that.deptNo) return false;
		if (dnombre != null ? !dnombre.equals(that.dnombre) : that.dnombre != null) return false;
		if (loc != null ? !loc.equals(that.loc) : that.loc != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = deptNo;
		result = 31 * result + (dnombre != null ? dnombre.hashCode() : 0);
		result = 31 * result + (loc != null ? loc.hashCode() : 0);
		return result;
	}

	public Collection<EmpleadosEntity> getEmpleadosByDeptNo() {
		return empleadosByDeptNo;
	}

	public void setEmpleadosByDeptNo(Collection<EmpleadosEntity> empleadosByDeptNo) {
		this.empleadosByDeptNo = empleadosByDeptNo;
	}
}