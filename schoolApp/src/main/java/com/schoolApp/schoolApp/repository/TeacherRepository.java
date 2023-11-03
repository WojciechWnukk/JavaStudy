package com.schoolApp.schoolApp.repository;

import com.schoolApp.schoolApp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
