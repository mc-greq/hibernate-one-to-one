package example.nio;

import example.nio.entities.Instructor;
import example.nio.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        try(factory){
            Session session = factory.getCurrentSession();

            // create the objects
//            Instructor instructor =
//                    new Instructor(
//                            "Grzegorz",
//                            "Rutkowski",
//                            "rutkowski@gmail.com"
//                    );
//            InstructorDetail instructorDetail =
//                    new InstructorDetail(
//                            "http://youtube.com",
//                            "sailing"
//                    );

            Instructor instructor =
                    new Instructor(
                            "Marta",
                            "Florczak",
                            "florczak@gmail.com"
                    );
            InstructorDetail instructorDetail =
                    new InstructorDetail(
                            "http://florczak.youtube.com",
                            "cleaning"
                    );

            // associate the objects
            instructor.setInstructorDetail(instructorDetail);

            session.beginTransaction();

            // save the instructor
            // NOTE: this will ALSO save the details object
            // because of CascadeType.ALL
            session.save(instructor);

            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
