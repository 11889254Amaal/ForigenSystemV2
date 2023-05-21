package com.example.Foreign.Affairs.Ministry.API.Services;

import com.example.Foreign.Affairs.Ministry.API.Modell.News;
import com.example.Foreign.Affairs.Ministry.API.Modell.Policy;
import com.example.Foreign.Affairs.Ministry.API.Repsitory.NewsRepository;
import com.example.Foreign.Affairs.Ministry.API.RequestObject.CreatePolicyRequest;
import com.example.Foreign.Affairs.Ministry.API.RequestObject.CreatenNewsRequest;
import com.example.Foreign.Affairs.Ministry.API.RequestObject.NewsRequestForUpdate;
import com.example.Foreign.Affairs.Ministry.API.RequestObject.PolicyRequestForUpdate;
import com.example.Foreign.Affairs.Ministry.API.Response.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class NewsServices {
    @Autowired
    NewsRepository newsRepository;

    public void CreateNewNews(CreatenNewsRequest newsRequest) throws ParseException {
        News NewsDetails = new News();
        NewsDetails.setCountryName(newsRequest.getCountryName());
        NewsDetails.setRegionName(newsRequest.getRegionName());
        NewsDetails.setTopicOfNews(newsRequest.getTopicOfNews());
        NewsDetails.setDetailsOfNews(newsRequest.getDetailsOfNews());
        NewsDetails.setStartDate(new Date());
        NewsDetails.setEndDate(newsRequest.getEndDate());
        NewsDetails.setIsActive(newsRequest.getIsActive());
        newsRepository.save(NewsDetails);

    }


    public void updateNewNews(NewsRequestForUpdate newsRequestForUpdate) throws ParseException {
        News NewsDetails = new News();
        NewsDetails.setId(newsRequestForUpdate.getId());
        NewsDetails.setCountryName(newsRequestForUpdate.getCountryName());
        NewsDetails.setRegionName(newsRequestForUpdate.getRegionName());
        NewsDetails.setTopicOfNews(newsRequestForUpdate.getTopicOfNews());
        NewsDetails.setDetailsOfNews(newsRequestForUpdate.getDetailsOfNews());
        NewsDetails.setStartDate(new Date());
        NewsDetails.setEndDate(newsRequestForUpdate.getEndDate());
        NewsDetails.setIsActive(newsRequestForUpdate.getIsActive());
        newsRepository.save(NewsDetails);

    }

    public void deleteNews(Integer id) {
        newsRepository.deleteNews(id);
    }

    public List<News> searchNews(String countryName, String regionName, String topicOfNews) {
        if (countryName != null) {
            return newsRepository.findByCountryName(countryName);
        } else if (regionName != null) {
            return newsRepository.findByRegionName(regionName);
        } else if (topicOfNews != null) {
            return newsRepository.findByTopicOfNews(topicOfNews);
        } else {
            return newsRepository.findAll();
        }
    }


//    private static final String apiKey = "5d18e651c46448d78e038ebeeab23d1a";
//    private static final String BASE_URL = "https://newsapi.org/v2/everything?q=tesla&from=2023-04-17&sortBy=publishedAt&";

    private final RestTemplate restTemplate = new RestTemplate();


    public Root getOneLinerNews() {
        String url = "https://newsapi.org/v2/everything?q=tesla&from=2023-04-18&sortBy=publishedAt&apiKey=5d18e651c46448d78e038ebeeab23d1a";
        Root response = restTemplate.getForObject(url, Root.class);
        return response;
    }

    public String getAllNews() {
        List<News> news = newsRepository.getAllNews();

        StringBuilder newsStringBuilder = new StringBuilder();
        for (News news1 : news) {
            newsStringBuilder.append("ID: ").append(news1.getId()).append("\n");
            newsStringBuilder.append("Country: ").append(news1.getCountryName()).append("\n");
            newsStringBuilder.append("Region: ").append(news1.getRegionName()).append("\n");
            newsStringBuilder.append("Title: ").append(news1.getTopicOfNews()).append("\n");
            newsStringBuilder.append("Details: ").append(news1.getDetailsOfNews()).append("\n");
            newsStringBuilder.append("\n");
        }

        return newsStringBuilder.toString();
    }

    public News getNewsById(Integer id){
        return newsRepository.getNewsById(id);

    }

}
