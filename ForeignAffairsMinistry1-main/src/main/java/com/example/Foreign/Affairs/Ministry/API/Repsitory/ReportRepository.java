package com.example.Foreign.Affairs.Ministry.API.Repsitory;

import com.example.Foreign.Affairs.Ministry.API.Modell.Policy;
import com.example.Foreign.Affairs.Ministry.API.Modell.ReportTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<ReportTable,Integer> {
    @Query(value = "SELECT m FROM ReportTable m")
    List<ReportTable> getAllUsageStatus();
}
