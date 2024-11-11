package app;

import java.util.List;
import java.util.Random;

import domain.Address;
import domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import repositories.StudentRepository;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class CustomerApplication implements CommandLineRunner{
    @Autowired
    StudentRepository studentRepository;

    @Profile("student")
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
        Student student;
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            // Generate random student details
            int id = 1000 + i;
            String name = "Student" + i;
            String phone = "06223" + (i * 10000);
            String email = "student" + id + "@example.com";

            // Create random address
            String street = (10 + i) + "th Street";
            String city = random.nextBoolean() ? "Queens" : "Brooklyn";
            String zipCode = "11" + (100 + i);

            // Create student and address
            student = new Student(id, name, phone, email);
            Address address = new Address(street, city, zipCode);
            student.setAddress(address);

            // Save to the repository
            studentRepository.save(student);

            System.out.println("Inserted Student: " + student);
        }


        //get students
        System.out.println("-----------Get all students ----------------");
		System.out.println(studentRepository.findAll());
        System.out.println("-----------Get all students with a certain name ----------------");
		System.out.println(studentRepository.findByName(""));
		System.out.println("-----------Get a student with a certain phoneNumber ----------------");
		System.out.println(studentRepository.findByPhone("0622330000"));
        System.out.println("-----------Get all students from a certain city ----------------");
		List<Student> students = studentRepository.findStudentWithCity("Queens");
		for (Student std : students){
			System.out.println(std);
		}

		
		//update student
		student = studentRepository.findById(1001).get();
		student.setEmail("jd@gmail.com");
		studentRepository.save(student);
		System.out.println(student);
		
    }
}
