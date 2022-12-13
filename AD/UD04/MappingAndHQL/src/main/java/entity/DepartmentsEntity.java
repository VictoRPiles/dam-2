package entity;

import javax.persistence.*;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DepartmentsEntity that = (DepartmentsEntity) o;

		if (deptNum != that.deptNum) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (office != null ? !office.equals(that.office) : that.office != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = deptNum;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (office != null ? office.hashCode() : 0);
		return result;
	}
}