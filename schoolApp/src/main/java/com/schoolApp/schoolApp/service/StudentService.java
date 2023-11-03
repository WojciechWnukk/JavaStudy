package com.schoolApp.schoolApp.service;

import com.schoolApp.schoolApp.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Long id);
}