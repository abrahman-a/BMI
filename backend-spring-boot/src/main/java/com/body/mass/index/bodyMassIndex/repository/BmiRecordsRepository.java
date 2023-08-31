package com.body.mass.index.bodyMassIndex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.body.mass.index.bodyMassIndex.model.BMIRecords;

public interface BmiRecordsRepository extends JpaRepository<BMIRecords, Long> {

    // Add any custom query methods if needed
}
