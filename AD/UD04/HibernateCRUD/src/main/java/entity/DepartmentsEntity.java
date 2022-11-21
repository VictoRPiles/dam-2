package entity;

import jakarta.persistence.*;

import java.util.Collection;

/**
 * @author Victor Piles
 */
@Entity
@Table(name = "departments", schema = "act4_1", catalog = "")
public class DepartmentsEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "dept_num")
	private int deptNum;
	@Basic
	@Column(name = "name")
	private String name;
	@Basic
	@Column(name = "office")
	private String office;
	@OneToMany(mappedBy = "departmentsByDeptNum")
	private Collection<TeachersEntity> teachersByDeptNum;

	public DepartmentsEntity() {
	}

	public DepartmentsEntity(int deptNum, String name, String office) {
		this.deptNum = deptNum;
		this.name = name;
		this.office = office;
	}

	public int getDeptNum() {
		return deptNum;
	}

	public void setDeptNum(int deptNum) {
		this.deptNum = deptNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public Collection<TeachersEntity> getTeachersByDeptNum() {
		return teachersByDeptNum;
	}

	public void setTeachersByDeptNum(Collection<TeachersEntity> teachersByDeptNum) {
		this.teachersByDeptNum = teachersByDeptNum;
	}

	@Override
	public String toString() {
		return "DepartmentsEntity{" +
				"deptNum=" + deptNum +
				", name='" + name + '\'' +
				", office='" + office + '\'' +
				'}';
	}
}