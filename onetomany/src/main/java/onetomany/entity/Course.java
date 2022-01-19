package onetomany.entity;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;

	@Column(name = "title")
	private String title;

	public Course() {

	}

	public Course(String title) {
		super();
		this.title = title;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return id == other.id && Objects.equals(instructor, other.instructor) && Objects.equals(title, other.title);
	}

	public int getId() {
		return id;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, instructor, title);
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}


}
