package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class CreateDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// create session
		
		Session session = factory.getCurrentSession();
		
		try {
			
			//create the objects
			/*Instructor tempInstructor =
					new Instructor("Ryan", "Cuvillier", "mr.cuvillier@gmail.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
					"http://www.youtube.com/ryanTube",
					"learning to code"); */
			
			Instructor tempInstructor =
					new Instructor("Bruce", "Lee", "brucLee@gmail.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
					"http://www.youtube.com/bruceLeeTube",
					"Jeet Kune Do");
			
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//start transaction
			session.beginTransaction();
			
			//save the instructor
			//
			//Note: this will also save the detail object
			//because of CascadeType.All
			//
			session.save(tempInstructor);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
