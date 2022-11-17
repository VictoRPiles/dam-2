package entity;

import jakarta.persistence.*;

import java.sql.Date;

/**
 * @author Victor Piles
 */
@Entity
@Table(name = "empleados", schema = "ejemplo", catalog = "")
public class EmpleadosEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "emp_no")
	private int empNo;
	@Basic
	@Column(name = "apellido")
	private String apellido;
	@Basic
	@Column(name = "oficio")
	private String oficio;
	@Basic
	@Column(name = "dir")
	private Integer dir;
	@Basic
	@Column(name = "fecha_alta")
	private Date fechaAlta;
	@Basic
	@Column(name = "salario")
	private Double salario;
	@Basic
	@Column(name = "comision")
	private Double comision;
	@ManyToOne
	@JoinColumn(name = "dept_no", referencedColumnName = "dept_no")
	private DepartamentosEntity departamentosByDeptNo;

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getOficio() {
		return oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public Integer getDir() {
		return dir;
	}

	public void setDir(Integer dir) {
		this.dir = dir;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Double getComision() {
		return comision;
	}

	public void setComision(Double comision) {
		this.comision = comision;
	}

	public DepartamentosEntity getDepartamentosByDeptNo() {
		return departamentosByDeptNo;
	}

	public void setDepartamentosByDeptNo(DepartamentosEntity departamentosByDeptNo) {
		this.departamentosByDeptNo = departamentosByDeptNo;
	}
}