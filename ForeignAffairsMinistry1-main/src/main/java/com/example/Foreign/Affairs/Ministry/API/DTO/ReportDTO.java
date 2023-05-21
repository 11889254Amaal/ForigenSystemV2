package com.example.Foreign.Affairs.Ministry.API.DTO;

public class ReportDTO {
    private String endPoint;

    private Integer totalRequests;
    private Integer totalErrors;
    private double averageResponseTime;

    public ReportDTO(String endPoint, Integer totalRequests, Integer totalErrors, double averageResponseTime) {
        this.endPoint = endPoint;
        this.totalRequests = totalRequests;
        this.totalErrors = totalErrors;
        this.averageResponseTime = averageResponseTime;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public Integer getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(Integer totalRequests) {
        this.totalRequests = totalRequests;
    }

    public Integer getTotalErrors() {
        return totalErrors;
    }

    public void setTotalErrors(Integer totalErrors) {
        this.totalErrors = totalErrors;
    }

    public double getAverageResponseTime() {
        return averageResponseTime;
    }

    public void setAverageResponseTime(double averageResponseTime) {
        this.averageResponseTime = averageResponseTime;
    }
}
