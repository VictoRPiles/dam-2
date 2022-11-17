package entity;

import jakarta.persistence.*;

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

	public Collection<EmpleadosEntity> getEmpleadosByDeptNo() {
		return empleadosByDeptNo;
	}

	public void setEmpleadosByDeptNo(Collection<EmpleadosEntity> empleadosByDeptNo) {
		this.empleadosByDeptNo = empleadosByDeptNo;
	}
}