package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class CreateCoursesAndReviewsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();

		// create session

		Session session = factory.getCurrentSession();

		try {

			// start transaction
			session.beginTransaction();

			// create the course
			Course tempCourse = new Course("Metal Gear Solid - Phantom Pain");

			// add some reviews
			tempCourse.addReview(new Review("Greatest class ever!!!"));
			tempCourse.addReview(new Review("Soo great!"));
			tempCourse.addReview(new Review("One of the best experiences!"));
			tempCourse.addReview(new Review("This is the dumbest class ever!"));

			// save the course ... and leverage the cascade all :-)
			System.out.println("Saving the course");
			System.out.println(tempCourse);
			System.out.println(tempCourse.getReviews());

			session.save(tempCourse);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {

			// add clean up code

			session.close();

			factory.close();
		}
	}

}
