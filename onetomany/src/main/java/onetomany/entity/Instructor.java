package onetomany.entity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "instructor")
public class Instructor {
	@OneToMany(mappedBy = "instructor", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private List<Course> courses;

	@Column(name = "email")
	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "instructor_detail_id")
	private InstructorDetail instructorDetail;

	@Column(name = "last_name")
	private String lastName;

	public Instructor() {

	}

	public Instructor(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Instructor other = (Instructor) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(instructorDetail, other.instructorDetail) && Objects.equals(lastName, other.lastName);
	}

	public List<Course> getCourses() {
		return courses;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public int getId() {
		return id;
	}

	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, instructorDetail, lastName);
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail = instructorDetail;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void add(Course course) {
		if (courses == null)
			courses = new ArrayList<>();

		courses.add(course);
		course.setInstructor(this);
	}

	@Override
	public String toString() {
		return "Instructor [courses=" + courses + ", email=" + email + ", firstName=" + firstName + ", id=" + id
				+ ", instructorDetail=" + instructorDetail + ", lastName=" + lastName + "]";
	}


}
