package com.schoolApp.schoolApp.service;

import com.schoolApp.schoolApp.model.Classes;
import com.schoolApp.schoolApp.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService{
    @Autowired
    private ClassesRepository classesRepository;

    @Override
    public List<Classes> getAllClasses() {
        return classesRepository.findAll();
    }

    @Override
    public Classes getClassesById(Long id) {
        return classesRepository.findById(id).orElse(null);
    }

    @Override
    public void addClasses(Classes classes) {
        classesRepository.save(classes);
    }

    @Override
    public void updateClasses(Classes classes) {
        classesRepository.save(classes);
    }

    @Override
    public void deleteClasses(Long id) {
        classesRepository.deleteById(id);
    }
}
