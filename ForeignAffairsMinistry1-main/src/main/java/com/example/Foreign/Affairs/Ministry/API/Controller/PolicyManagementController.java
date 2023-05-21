package com.example.Foreign.Affairs.Ministry.API.Controller;


import com.example.Foreign.Affairs.Ministry.API.Mailling.EmailDetails;
import com.example.Foreign.Affairs.Ministry.API.Mailling.EmailService;

import com.example.Foreign.Affairs.Ministry.API.Modell.Policy;
import com.example.Foreign.Affairs.Ministry.API.Modell.ReportTable;
import com.example.Foreign.Affairs.Ministry.API.Repsitory.ReportRepository;
import com.example.Foreign.Affairs.Ministry.API.RequestObject.CreatePolicyRequest;
import com.example.Foreign.Affairs.Ministry.API.RequestObject.PolicyRequestForUpdate;
import com.example.Foreign.Affairs.Ministry.API.ScheduleJobs.Schedule;
import com.example.Foreign.Affairs.Ministry.API.Services.PolicyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "policyManagement")
public class PolicyManagementController {
    @Autowired
    PolicyServices policyServices;

    @Autowired
    EmailService emailService;
  @Autowired
    ReportRepository reportRepository;
    @Autowired
    Schedule scheduleDetails;
    @PostMapping(value = "/createPolicy")
    public String createCustomer(@RequestBody CreatePolicyRequest createPolicyRequest) throws ParseException {
        String endPoint="/api/polices";
        long startTime = System.currentTimeMillis();
        boolean isSuccess = false;
        try {
            isSuccess = true;
            policyServices.CreateNewPolicy(createPolicyRequest);
            return " Policy created Successfully ";
        } catch (Exception e) {
            ReportTable usageStatus =reportRepository.getById(2);
            if (usageStatus.getErrorCount() == null) {
                usageStatus.setErrorCount(1);
            } else {
                usageStatus.setErrorCount(usageStatus.getErrorCount() + 1);
            }
            reportRepository.save(usageStatus);
            return "create new Policy failed";
        } finally {
            long endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;

            ReportTable usageStatus = reportRepository.getById(2);

            if (isSuccess) {
                usageStatus.setEndpoint(endPoint);
                usageStatus.incrementRequestCount();
                usageStatus.addResponseTime(responseTime);
            }

            if (usageStatus.getTotalErrors() == null) {
                usageStatus.setTotalErrors(usageStatus.getErrorCount());
            } else {
                usageStatus.setTotalErrors(usageStatus.getTotalErrors() + usageStatus.getErrorCount());
            }
            usageStatus.setErrorCount(0);

            reportRepository.save(usageStatus);
        }


    }

    @RequestMapping(value = "/updatePolicy", method = RequestMethod.POST)
    public String updateCustomer(@RequestBody PolicyRequestForUpdate policyRequestForUpdate) throws ParseException {
        long startTime = System.currentTimeMillis();
        boolean isSuccess = false;
        try {
            isSuccess = true;
            policyServices.updateNewPolicy(policyRequestForUpdate);
            return " Policy updated Successfully ";
        } catch (Exception e) {
            ReportTable usageStatus = reportRepository.getById(2);
            if (usageStatus.getErrorCount() == null) {
                usageStatus.setErrorCount(1);
            } else {
                usageStatus.setErrorCount(usageStatus.getErrorCount() + 1);
            }
            reportRepository.save(usageStatus);
            return "create new Policy failed";

        } finally {
            long endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;

            ReportTable usageStatus = reportRepository.getById(2);

            if (isSuccess) {
                usageStatus.incrementRequestCount();
                usageStatus.addResponseTime(responseTime);
            }

            if (usageStatus.getTotalErrors() == null) {
                usageStatus.setTotalErrors(usageStatus.getErrorCount());
            } else {
                usageStatus.setTotalErrors(usageStatus.getTotalErrors() + usageStatus.getErrorCount());
            }
            usageStatus.setErrorCount(0);

            reportRepository.save(usageStatus);
        }

    }

    @RequestMapping(value = "/deletePolicy", method = RequestMethod.GET)
    public String deletePolicy(Integer id) {
        long startTime = System.currentTimeMillis();
        boolean isSuccess = false;
        try {
            isSuccess = true;
            policyServices.deletePolicy(id);
            return "Policy deleted Successfully ";
        } catch (Exception e) {
            ReportTable usageStatus = reportRepository.getById(2);
            if (usageStatus.getErrorCount() == null) {
                usageStatus.setErrorCount(1);
            } else {
                usageStatus.setErrorCount(usageStatus.getErrorCount() + 1);
            }
            reportRepository.save(usageStatus);
            return "Delete Policy failed";
        } finally {
            long endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;

            ReportTable usageStatus = reportRepository.getById(2);

            if (isSuccess) {
                usageStatus.incrementRequestCount();
                usageStatus.addResponseTime(responseTime);
            }

            if (usageStatus.getTotalErrors() == null) {
                usageStatus.setTotalErrors(usageStatus.getErrorCount());
            } else {
                usageStatus.setTotalErrors(usageStatus.getTotalErrors() + usageStatus.getErrorCount());
            }
            usageStatus.setErrorCount(0);

            reportRepository.save(usageStatus);
        }

    }

    @GetMapping(value = "/Search")
    public ResponseEntity<List<Policy>> searchPolicies(
            @RequestParam(value = "countryName", required = false) String countryName,
            @RequestParam(value = "regionName", required = false) String regionName,
            @RequestParam(value = "titleOfPolicy", required = false) String c
    ) {
        {
            long startTime = System.currentTimeMillis();
            boolean isSuccess = false;
            try {
                isSuccess = true;
                List<Policy> policies = policyServices.searchPolicies(countryName, regionName, regionName);
                return ResponseEntity.ok(policies);
            } catch (Exception e) {
                ReportTable usageStatus = reportRepository.getById(2);
                if (usageStatus.getErrorCount() == null) {
                    usageStatus.setErrorCount(1);
                } else {
                    usageStatus.setErrorCount(usageStatus.getErrorCount() + 1);
                }
                reportRepository.save(usageStatus);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            } finally {
                long endTime = System.currentTimeMillis();
                long responseTime = endTime - startTime;

                ReportTable usageStatus = reportRepository.getById(2);

                if (isSuccess) {
                    usageStatus.incrementRequestCount();
                    usageStatus.addResponseTime(responseTime);
                }

                if (usageStatus.getTotalErrors() == null) {
                    usageStatus.setTotalErrors(usageStatus.getErrorCount());
                } else {
                    usageStatus.setTotalErrors(usageStatus.getTotalErrors() + usageStatus.getErrorCount());
                }
                usageStatus.setErrorCount(0);

                reportRepository.save(usageStatus);
            }

        }


    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/getAllPolicies")
    public ResponseEntity<String> getAllPolicies() {
        long startTime = System.currentTimeMillis();
        boolean isSuccess = false;
        try {
            isSuccess = true;
            String policiesAsString = policyServices.getAllPoliciesAsString();
            return ResponseEntity.ok(policiesAsString);
        } catch (Exception e) {
            ReportTable usageStatus = reportRepository.getById(2);
            if (usageStatus.getErrorCount() == null) {
                usageStatus.setErrorCount(1);
            } else {
                usageStatus.setErrorCount(usageStatus.getErrorCount() + 1);
            }
            reportRepository.save(usageStatus);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            long endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;

            ReportTable usageStatus = reportRepository.getById(2);

            if (isSuccess) {
                usageStatus.incrementRequestCount();
                usageStatus.addResponseTime(responseTime);
            }

            if (usageStatus.getTotalErrors() == null) {
                usageStatus.setTotalErrors(usageStatus.getErrorCount());
            } else {
                usageStatus.setTotalErrors(usageStatus.getTotalErrors() + usageStatus.getErrorCount());
            }
            usageStatus.setErrorCount(0);

            reportRepository.save(usageStatus);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Policy> getPolicyById(@PathVariable("id") Integer id) {
        long startTime = System.currentTimeMillis();
        boolean isSuccess = false;
        try {
            isSuccess = true;
            Policy policy = policyServices.getPolicyById(id);
            return ResponseEntity.ok(policy);
        } catch (Exception e) {
            ReportTable usageStatus = reportRepository.getById(2);
            if (usageStatus.getErrorCount() == null) {
                usageStatus.setErrorCount(1);
            } else {
                usageStatus.setErrorCount(usageStatus.getErrorCount() + 1);
            }
            reportRepository.save(usageStatus);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            long endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;

            ReportTable usageStatus = reportRepository.getById(2);

            if (isSuccess) {
                usageStatus.incrementRequestCount();
                usageStatus.addResponseTime(responseTime);
            }

            if (usageStatus.getTotalErrors() == null) {
                usageStatus.setTotalErrors(usageStatus.getErrorCount());
            } else {
                usageStatus.setTotalErrors(usageStatus.getTotalErrors() + usageStatus.getErrorCount());
            }
            usageStatus.setErrorCount(0);

            reportRepository .save(usageStatus);
        }
    }

}