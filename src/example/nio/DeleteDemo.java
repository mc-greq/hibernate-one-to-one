package example.nio;

import example.nio.entities.Instructor;
import example.nio.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try(factory){
            session.beginTransaction();

            // get instructor by primary key
            int instructorId = 1;
            Instructor instructor = session.get(Instructor.class,instructorId);

            System.out.println("Found instructor: " + instructor);

            // delete instructor
            if(instructor != null){
                System.out.println("Deleting: " + instructor);

                // NOTE this is also delete InstructorDetails object!
                // because of CascadeType.ALL
                session.delete(instructor);
            }

            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
