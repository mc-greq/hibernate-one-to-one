package example.nio;

import example.nio.entities.Instructor;
import example.nio.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try(factory; session){
            session.beginTransaction();

            // get the instructor detail object
            int id = 2;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
            System.out.println("Instructor detail: " + instructorDetail);

            // delete the instructor detail
            // NOTE this will also delete the associated instructor
            System.out.println("Deleting: " + instructorDetail);
            session.delete(instructorDetail);

            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
