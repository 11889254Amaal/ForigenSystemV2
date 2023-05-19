package com.example.Foreign.Affairs.Ministry.API.Controller;


import com.example.Foreign.Affairs.Ministry.API.Mailling.EmailDetails;
import com.example.Foreign.Affairs.Ministry.API.Mailling.EmailService;

import com.example.Foreign.Affairs.Ministry.API.Modell.Policy;
import com.example.Foreign.Affairs.Ministry.API.RequestObject.CreatePolicyRequest;
import com.example.Foreign.Affairs.Ministry.API.RequestObject.PolicyRequestForUpdate;
import com.example.Foreign.Affairs.Ministry.API.ScheduleJobs.Schedule;
import com.example.Foreign.Affairs.Ministry.API.Services.PolicyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    Schedule scheduleDetails;
    @PostMapping(value = "/createPolicy")
    public String createCustomer(@RequestBody CreatePolicyRequest createPolicyRequest) throws ParseException {
        try {
            policyServices.CreateNewPolicy(createPolicyRequest);

            return " new Polic add Successfully ";
        } catch (Exception e) {

            return "Polic filed  to add";
        }

    }

    @RequestMapping(value = "/updatePolicy", method = RequestMethod.POST)
    public String updateCustomer(@RequestBody PolicyRequestForUpdate policyRequestForUpdate) throws ParseException {
        try {
            policyServices.updateNewPolicy(policyRequestForUpdate);
            return " Policy Information updated Successfully ";
        } catch (Exception e) {
            return "update Policy Information  failed";
        }

    }

    @RequestMapping(value = "/deletePolicy", method = RequestMethod.GET)
    public String deletePolicy(Integer id) {
        try {
            policyServices.deletePolicy(id);
            return " Policy deleted Successfully ";
        } catch (Exception e) {
            return "Policy delete failed";
        }

    }

    @GetMapping(value = "/Search")
    public ResponseEntity<List<Policy>> searchPolicies(
            @RequestParam(value = "countryName", required = false) String countryName,
            @RequestParam(value = "regionName", required = false) String regionName,
            @RequestParam(value = "titleOfPolicy", required = false) String topicOfNews
    ) {
        List<Policy> policies = policyServices.searchPolicies(countryName, regionName, topicOfNews);
        return ResponseEntity.ok(policies);
    }

//    @GetMapping(value="/EmailMessageAndSceduallForAddPolicyFromApi")
//    public void message(@RequestBody EmailDetails details) {
//        emailService.sendSimpleMail(details);
//        //scheduleDetails.addPolicyFromExternalApi(details);
//    }
//
//    @GetMapping(value="/EmailMessageAndSceduallForUpdatePolicyFromApi")
//    public void messageUpdate(@RequestBody EmailDetails details) {
//        emailService.sendSimpleMail(details);
//        //scheduleDetails.UpdatePolicyFromExternalApi(details);
//    }
}
