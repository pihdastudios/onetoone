import entity.InstructorDetail;
import entity.Instructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
//    public static void main(String[] args) {
//        var jdbcUrl = "jdbc:mariadb://localhost:3306/hb-01-one-to-one-uni?useSsl=false";
//        var user = "root";
//        var pass = "S3cret";
//
//        try {
//            System.out.println("Connecting to database: " + jdbcUrl);
//            Class.forName ("org.mariadb.jdbc.Driver");
//            Connection myConn =
//                    DriverManager.getConnection(jdbcUrl, user, pass);
//
//            System.out.println("Connection successful!!!");
//
//        } catch (Exception exc) {
//            exc.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session

        try (factory) {
            Session session = factory.getCurrentSession();

            // create the objects
			/*
			Instructor tempInstructor =
					new Instructor("Chad", "Darby", "darby@luv2code.com");

			InstructorDetail tempInstructorDetail =
					new InstructorDetail(
							"http://www.luv2code.com/youtube",
							"Luv 2 code!!!");
			*/

            Instructor tempInstructor =
                    new Instructor("Madhu", "Patel", "madhu@luv2code.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail(
                            "http://www.youtube.com",
                            "Guitar");

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

    //    public static void main(String[] args) {
//        // create session factory
//        SessionFactory factory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Instructor.class)
//                .addAnnotatedClass(InstructorDetail.class)
//                .buildSessionFactory();
//
//        // create session
//
//        try (factory) {
//            Session session = factory.getCurrentSession();
//
//
//            /// Delete
//            session.beginTransaction();
//            var tempInstructor = session.get(Instructor.class, 1);
//            if (tempInstructor != null) {
//                session.delete(tempInstructor);
//                session.getTransaction().commit();
//            }
//
//        }
//    }


//    public static void main(String[] args) {
//        // create session factory
//        SessionFactory factory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Instructor.class)
//                .addAnnotatedClass(InstructorDetail.class)
//                .buildSessionFactory();
//
//        // create session
//
//        try (factory) {
//            Session session = factory.getCurrentSession();
//
//
//            /// Delete
//            session.beginTransaction();
//            var tempInstructorDetail = session.get(InstructorDetail.class, 3);
//            if (tempInstructorDetail != null) {
//                System.out.println(tempInstructorDetail.getInstructor());
//                tempInstructorDetail.getInstructor().setInstructorDetail(null);
//                session.delete(tempInstructorDetail);
//            }
//            session.getTransaction().commit();
//
//        }
//    }

}
