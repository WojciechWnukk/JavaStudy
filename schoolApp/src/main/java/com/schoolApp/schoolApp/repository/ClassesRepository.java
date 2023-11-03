package com.schoolApp.schoolApp.repository;

import com.schoolApp.schoolApp.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {

}
