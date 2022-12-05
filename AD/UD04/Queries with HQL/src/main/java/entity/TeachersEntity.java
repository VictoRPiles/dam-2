package entity;

import jakarta.persistence.*;

import java.sql.Date;

/**
 * @author Victor Piles
 */
@Entity
@Table(name = "teachers", schema = "act4_1", catalog = "")
public class TeachersEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "name")
	private String name;
	@Basic
	@Column(name = "surname")
	private String surname;
	@Basic
	@Column(name = "email")
	private String email;
	@Basic
	@Column(name = "start_date")
	private Date startDate;
	@Basic
	@Column(name = "salary")
	private Integer salary;
	@ManyToOne
	@JoinColumn(name = "dept_num", referencedColumnName = "dept_num")
	private DepartmentsEntity departmentsByDeptNum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public DepartmentsEntity getDepartmentsByDeptNum() {
		return departmentsByDeptNum;
	}

	public void setDepartmentsByDeptNum(DepartmentsEntity departmentsByDeptNum) {
		this.departmentsByDeptNum = departmentsByDeptNum;
	}
}