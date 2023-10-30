package com.clinic.clinic.repository;

import com.clinic.clinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {



}
