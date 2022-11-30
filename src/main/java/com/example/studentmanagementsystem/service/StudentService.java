package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.model.Login;
import com.example.studentmanagementsystem.model.Student;
import org.apache.juli.logging.Log;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student saveStudent(Student student);


    Student getStudentById(Long id);

    Student updateStudent(Student student);

    void deleteStudentById(Long id);
}