package com.schoolApp.schoolApp.service;

import com.schoolApp.schoolApp.model.Classes;
import com.schoolApp.schoolApp.model.Teacher;
import com.schoolApp.schoolApp.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Transactional
    public void assignClassesToTeacher(Long teacherId, Set<Classes> classes) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        System.out.println(teacher +" dasdaaaaaaaaaa");
        if (teacher != null) {
            teacher.setClasses(classes);
            System.out.println(classes +" dasdaaaaaaaaaa" + teacher.getClasses());
            teacherRepository.save(teacher);
        }
    }
}
