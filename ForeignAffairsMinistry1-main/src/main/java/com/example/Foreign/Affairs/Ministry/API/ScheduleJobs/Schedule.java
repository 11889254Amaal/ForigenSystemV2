package com.example.Foreign.Affairs.Ministry.API.ScheduleJobs;

import com.example.Foreign.Affairs.Ministry.API.Mailling.EmailDetails;
import com.example.Foreign.Affairs.Ministry.API.Mailling.EmailService;
import com.example.Foreign.Affairs.Ministry.API.Modell.News;
import com.example.Foreign.Affairs.Ministry.API.Modell.Policy;
import com.example.Foreign.Affairs.Ministry.API.Repsitory.NewsRepository;
import com.example.Foreign.Affairs.Ministry.API.Repsitory.PolicyRepository;
import com.example.Foreign.Affairs.Ministry.API.Response.Article;

import com.example.Foreign.Affairs.Ministry.API.Response.Root;
import com.example.Foreign.Affairs.Ministry.API.Services.NewsServices;
import com.example.Foreign.Affairs.Ministry.API.Services.PolicyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class Schedule {
    @Autowired
    PolicyRepository policyRepository;

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    NewsServices newsServices;

    @Autowired
    EmailService emailService;


    @Autowired
    PolicyServices policyServices;



   // @Scheduled(cron = "0 0 0 * * *")
   @Scheduled(cron = "0 0 0 * * *")
    public void addNewsFromExternalApi() {
       EmailDetails news=new EmailDetails();
        Root newsResponse = newsServices.getOneLinerNews();
        List<Article> articalList=newsResponse.getArticles();
        News NewsDetails = new News();
        for (Article artical:articalList)
        {
            NewsDetails.setCountryName(news.getCountryName());
            NewsDetails.setRegionName(news.getRegionName());
            NewsDetails.setIsActive(news.getIsActive());
            NewsDetails.setEndDate(news.getEndDate());
            String title = artical.getTitle();
            String desc = artical.getDescription();
            NewsDetails.setTopicOfNews(title);
            NewsDetails.setDetailsOfNews(desc);
            emailService.sendSimpleMail(news);
            newsRepository.save(NewsDetails);
        }

    }

    @Scheduled(cron = "0 0 0 * * *")
    public void UpdateNewsFromExternalApi() {
        EmailDetails news=new EmailDetails();

        Root newsResponse = newsServices.getOneLinerNews();
        List<Article> articalList=newsResponse.getArticles();
        News NewsDetails = new News();
        for (Article artical:articalList)
        {
            NewsDetails.setId(news.getId());
            NewsDetails.setCountryName(news.getCountryName());
            NewsDetails.setRegionName(news.getRegionName());
            NewsDetails.setIsActive(news.getIsActive());
            NewsDetails.setEndDate(news.getEndDate());
            String title = artical.getTitle();
            String desc = artical.getDescription();
            NewsDetails.setTopicOfNews(title);
            NewsDetails.setDetailsOfNews(desc);
            newsRepository.save(NewsDetails);
            emailService.sendSimpleMail(news);
        }

    }
    @Scheduled(cron = "0 0 0 * * *")
    public void addPolicyFromExternalApi() {
        EmailDetails policy=new EmailDetails();

        Root policyResponse = policyServices.getOneLinerNews();
        List<Article> articleListl=policyResponse.getArticles();
        Policy PolicyDetails = new Policy();
        for (Article article:articleListl)
        {
            PolicyDetails.setCountryName(policy.getCountryName());
            PolicyDetails.setRegionName(policy.getRegionName());
            PolicyDetails.setIsActive(policy.getIsActive());
            PolicyDetails.setEndDate(policy.getEndDate());
            String title = article.getTitle();
            String desc = article.getDescription();
            PolicyDetails.setTitleOfPolicy(title);
            PolicyDetails.setDetailsOfPolicy(desc);
            policyRepository.save(PolicyDetails);
            emailService.sendSimpleMail(policy);
        }

    }

    @Scheduled(cron = "0 0 0 * * *")
    public void UpdatePolicyFromExternalApi() {
        EmailDetails policy=new EmailDetails();

        Root policyResponse = policyServices.getOneLinerNews();
        List<Article> articleListl=policyResponse.getArticles();
        Policy PolicyDetails = new Policy();
        for (Article article:articleListl)
        {
            PolicyDetails.setId(policy.getId());
            PolicyDetails.setCountryName(policy.getCountryName());
            PolicyDetails.setRegionName(policy.getRegionName());
            PolicyDetails.setIsActive(policy.getIsActive());
            PolicyDetails.setEndDate(policy.getEndDate());
            String title = article.getTitle();
            String desc = article.getDescription();
            PolicyDetails.setTitleOfPolicy(title);
            PolicyDetails.setDetailsOfPolicy(desc);
            policyRepository.save(PolicyDetails);
            emailService.sendSimpleMail(policy);
        }

    }

}
