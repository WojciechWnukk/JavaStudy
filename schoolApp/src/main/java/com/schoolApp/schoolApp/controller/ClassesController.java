package com.schoolApp.schoolApp.controller;

import com.schoolApp.schoolApp.model.Classes;
import com.schoolApp.schoolApp.model.Student;
import com.schoolApp.schoolApp.service.ClassesService;
import com.schoolApp.schoolApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassesController {
    @Autowired
    private ClassesService classesService;
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Classes> getAllClasses() {
        return classesService.getAllClasses();
    }

    @PostMapping
    public void addClasses(@RequestBody Classes classes) {
        classesService.addClasses(classes);
    }


    @PutMapping("/{id}")
    public void updateClasses(@PathVariable Long id, @RequestBody Classes classes) {
        classes.setId(id);
        classesService.updateClasses(classes);
    }

    @PostMapping("/{classId}/addStudent/{studentId}")
    public void addStudentToClass(@PathVariable Long classId, @PathVariable Long studentId) {
        Classes classes = classesService.getClassesById(classId);
        Student student = studentService.getStudentById(studentId);

        if (classes != null && student != null) {
            classes.getStudents().add(student);
            classesService.updateClasses(classes);
        }
    }

    @DeleteMapping("/{classId}/removeStudent/{studentId}")
    public void removeStudentFromClass(@PathVariable Long classId, @PathVariable Long studentId) {
        Classes classes = classesService.getClassesById(classId);
        Student student = studentService.getStudentById(studentId);

        if (classes != null && student != null) {
            classes.getStudents().remove(student);
            classesService.updateClasses(classes);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteClasses(@PathVariable Long id) {
        classesService.deleteClasses(id);
    }
}
