package com.schoolApp.schoolApp.service;

import com.schoolApp.schoolApp.model.Classes;
import com.schoolApp.schoolApp.model.Teacher;

import java.util.List;
import java.util.Set;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    Teacher getTeacherById(Long id);
    void addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacher(Long id);
    void assignClassesToTeacher(Long teacherId, Set<Classes> classes);
}
