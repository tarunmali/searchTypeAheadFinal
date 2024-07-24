package com.example.searchTypeAheadFinal;
//package exceptionhandling.classroom.searchTypeAheadFinal.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrequencyCountRepository extends JpaRepository<FrequencyCount, Long> {

    FrequencyCount findByQuery(String query);

}
