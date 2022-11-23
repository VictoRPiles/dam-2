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
	@Column(name = "dept_num")
	private Integer deptNum;
	@Basic
	@Column(name = "salary")
	private Integer salary;

	public TeachersEntity() {
	}

	public TeachersEntity(int id, String name, String surname, String email, Date startDate, Integer deptNum, Integer salary) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.startDate = startDate;
		this.deptNum = deptNum;
		this.salary = salary;
	}

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

	public Integer getDeptNum() {
		return deptNum;
	}

	public void setDeptNum(Integer deptNum) {
		this.deptNum = deptNum;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "TeachersEntity{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", email='" + email + '\'' +
				", startDate=" + startDate +
				", deptNum=" + deptNum +
				", salary=" + salary +
				'}';
	}
}