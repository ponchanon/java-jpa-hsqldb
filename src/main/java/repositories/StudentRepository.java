package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
    Student findByName(String name);
    Student findByPhone(String phone);
    //Student findByCity(String city);
    List<Student> findByNameOrPhone(String name, String phone);

    @Query("select s from Student s")
    List<Student>  findStudentAll();

    @Query("select s from Student s where s.name= :name")
    List<Student>  findStudentWithName(String name);

    @Query("select s from Student s where s.phone= :phone")
    Student findStudentWithPhone(String phone);

    @Query("select s from Student s where s.address.city= :city")
    List<Student>  findStudentWithCity(String city);
}
