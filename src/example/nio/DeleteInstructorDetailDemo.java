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
            int id = 3;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
            System.out.println("Instructor detail: " + instructorDetail);

            // delete the instructor detail
            // break bi-directional link
            instructorDetail.getInstructor().setInstructorDetail(null);
            System.out.println("Deleting: " + instructorDetail);
            // now we can safely delete instructorDetail
            session.delete(instructorDetail);

            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
