package com.example.Foreign.Affairs.Ministry.API.Services;

import com.example.Foreign.Affairs.Ministry.API.Modell.Policy;
import com.example.Foreign.Affairs.Ministry.API.Repsitory.PolicyRepository;
import com.example.Foreign.Affairs.Ministry.API.RequestObject.CreatePolicyRequest;
import com.example.Foreign.Affairs.Ministry.API.RequestObject.PolicyRequestForUpdate;
import com.example.Foreign.Affairs.Ministry.API.Response.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class PolicyServices {

@Autowired
    PolicyRepository policyRepository;

    public void CreateNewPolicy(CreatePolicyRequest policyRequest) throws ParseException {
        Policy policyDetails = new Policy();
        policyDetails.setCountryName(policyRequest.getCountryName());
        policyDetails.setRegionName(policyRequest.getRegionName());
        policyDetails.setTitleOfPolicy(policyRequest.getTitleOfPolicy());
        policyDetails.setDetailsOfPolicy(policyRequest.getDetailsOfPolicy());
        policyDetails.setStartDate(new Date());
        policyDetails.setEndDate(policyRequest.getEndDate());
        policyDetails.setIsActive(policyRequest.getIsActive());
        policyRepository.save(policyDetails);

    }



    public void updateNewPolicy(PolicyRequestForUpdate policyRequestForUpdate) throws ParseException {
        Policy policyDetails = new Policy();
        policyDetails.setId(policyRequestForUpdate.getId());
        policyDetails.setCountryName(policyRequestForUpdate.getCountryName());
        policyDetails.setRegionName(policyRequestForUpdate.getRegionName());
        policyDetails.setTitleOfPolicy(policyRequestForUpdate.getTitleOfPolicy());
        policyDetails.setDetailsOfPolicy(policyRequestForUpdate.getDetailsOfPolicy());
        policyDetails.setEndDate(policyRequestForUpdate.getEndDate());
        policyDetails.setStartDate(new Date());
        policyDetails.setIsActive(policyRequestForUpdate.getIsActive());
        policyRepository.save(policyDetails);

    }

    public void deletePolicy(Integer id) {
        policyRepository.deletePolicy(id);
    }

    public List<Policy> searchPolicies(String countryName, String regionName, String titleOfPolicy) {
        if (countryName != null) {
            return policyRepository.findByCountryName(countryName);
        } else if (regionName != null) {
            return policyRepository.findByRegionName(regionName);
        } else if (titleOfPolicy != null) {
            return policyRepository.findByTitleOfPolicy(titleOfPolicy);
        } else {
            return policyRepository.findAll();
        }
    }
    private final RestTemplate restTemplate = new RestTemplate();
    public Root getOneLinerNews() {
        String url = "https://newsapi.org/v2/everything?q=tesla&from=2023-04-22&sortBy=publishedAt&apiKey=bde4b4791fa24aa2a8a88acafae6147f";
        Root response = restTemplate.getForObject(url, Root.class);
        return response;
    }

    public String getAllPoliciesAsString() {
        List<Policy> policies = policyRepository.getAllPolices();

        StringBuilder policiesStringBuilder = new StringBuilder();
        for (Policy policy : policies) {
            policiesStringBuilder.append("ID: ").append(policy.getId()).append("\n");
            policiesStringBuilder.append("Country: ").append(policy.getCountryName()).append("\n");
            policiesStringBuilder.append("Region: ").append(policy.getRegionName()).append("\n");
            policiesStringBuilder.append("Topic: ").append(policy.getTitleOfPolicy()).append("\n");
            policiesStringBuilder.append("Details: ").append(policy.getDetailsOfPolicy()).append("\n");
            policiesStringBuilder.append("\n");
        }

        return policiesStringBuilder.toString();
    }


    public Policy getPolicyById(Integer id){
        return policyRepository.getPolicyById(id);

    }
}
