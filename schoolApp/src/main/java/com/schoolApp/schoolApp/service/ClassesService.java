package com.schoolApp.schoolApp.service;

import com.schoolApp.schoolApp.model.Classes;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ClassesService {
    List<Classes> getAllClasses();
    Classes getClassesById(Long id);
    void addClasses(Classes classes);
    void updateClasses(Classes classes);
    void deleteClasses(Long id);
}
