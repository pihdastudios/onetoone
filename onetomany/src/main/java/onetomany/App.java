package onetomany;

import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import onetomany.entity.Course;
import onetomany.entity.Instructor;
import onetomany.entity.InstructorDetail;

public class App {

	public static void main(String[] args) {
		deleteCourse();
	}

	public static void createInstructor() {
		// create session factory
		var factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		var session = factory.getCurrentSession();

		try (factory; session) {

			// create the objects
			var tempInstructor = new Instructor("Susan", "Public", "susan.public@luv2code.com");

			var tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "Video Games");

			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);

			// start a transaction
			session.beginTransaction();

			// save the instructor
			//
			// Note: this will ALSO save the details object
			// because of CascadeType.ALL
			//
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		}
	}

	public static void createCourse() {
		// create session factory
		var factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		var session = factory.getCurrentSession();

		try (factory; session) {

			// start a transaction
			session.beginTransaction();

			var instructor = session.get(Instructor.class, 1);

			var course = new Course("Air Guitar - The Ultimate Guide");
			var course1 = new Course("Pinball Masterclass");

			instructor.add(course);
			instructor.add(course1);

			session.save(course);
			session.save(course1);


			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		}
	}
	
	public static void getInstructor() {
		// create session factory
		var factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		var session = factory.getCurrentSession();

		try (factory; session) {

			// start a transaction
			session.beginTransaction();

			var instructor = session.get(Instructor.class, 1);

			System.out.println(instructor);
			System.out.println(instructor.getCourses());

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		}
	}
	
	public static void deleteCourse() {
		var factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		var session = factory.getCurrentSession();

		try (factory; session) {

			// start a transaction
			session.beginTransaction();

			// Get Course
			var course = session.get(Course.class, 10);
			session.delete(course);
			// Delete Course
			


			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		}
	}

}
