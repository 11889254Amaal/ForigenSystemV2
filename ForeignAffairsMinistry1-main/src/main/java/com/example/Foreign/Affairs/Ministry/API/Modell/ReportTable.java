package com.example.Foreign.Affairs.Ministry.API.Modell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Data
@Entity
@AllArgsConstructor
public class ReportTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String endpoint;
    private Integer errorCount;
    private Integer totalRequests;
    private Integer totalErrors;
    private double averageResponseTime;


    public void incrementErrorCount() {
        if (errorCount == null) {
            errorCount = 0;
        }
        errorCount++;
    }

    public void incrementRequestCount() {
        if (totalRequests == null) {
            totalRequests = 0;
        }
        totalRequests++;
    }

    public void addResponseTime(long responseTime) {
        if (averageResponseTime == 0) {
            averageResponseTime = responseTime;
        } else {
            averageResponseTime = (averageResponseTime + responseTime) / 2;
        }
    }
}
