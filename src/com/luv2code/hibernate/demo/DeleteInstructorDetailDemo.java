package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session

		Session session = factory.getCurrentSession();

		try {

			// start transaction
			session.beginTransaction();

			// get the instructor detail object
			int tempId = 3;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, tempId);

			// print the instructor detail
			System.out.println("TempInstructorDetail: " + tempInstructorDetail);

			// print the associated instructor
			System.out.println("Associated Instructor: " + tempInstructorDetail.getInstructor());
			
			//delete the instructor detail
			//break bi-directional link
			
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			System.out.println("Deleteing tempInstructorDetail: " 
											+ tempInstructorDetail);
			//remove the associate object reference
			session.delete(tempInstructorDetail);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Committing....");
			System.out.println("Done!");
		} 
		catch(Exception exc) {
			exc.printStackTrace();
		}	
		finally {
			//handle connection leak issue
			session.close();
			factory.close();
		}
	}

}
