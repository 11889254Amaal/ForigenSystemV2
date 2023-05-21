package com.example.Foreign.Affairs.Ministry.API.Controller;

import com.example.Foreign.Affairs.Ministry.API.Mailling.EmailService;
import com.example.Foreign.Affairs.Ministry.API.Modell.News;
import com.example.Foreign.Affairs.Ministry.API.Modell.ReportTable;
import com.example.Foreign.Affairs.Ministry.API.Repsitory.NewsRepository;
import com.example.Foreign.Affairs.Ministry.API.Repsitory.ReportRepository;
import com.example.Foreign.Affairs.Ministry.API.RequestObject.CreatenNewsRequest;
import com.example.Foreign.Affairs.Ministry.API.RequestObject.NewsRequestForUpdate;
import com.example.Foreign.Affairs.Ministry.API.ScheduleJobs.Schedule;
import com.example.Foreign.Affairs.Ministry.API.Services.NewsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping(value = "NewsManagement")
public class NewsManagement {

    @Autowired
    NewsServices newsServices;

    @Autowired
    EmailService emailService;
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    Schedule scheduleDetails;
    @Autowired
    NewsRepository newsRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/createNews", method = RequestMethod.POST)
    public String createCustomer(@RequestBody CreatenNewsRequest createnNewsRequest)throws ParseException {
        long startTime = System.currentTimeMillis();
        String endPoint="NewsManagement";
        boolean isSuccess = false;
        try {
            newsServices.CreateNewNews(createnNewsRequest);
            isSuccess = true;
            return " new News add Successfully ";
        } catch (Exception e) {
            ReportTable usageStatus = reportRepository.getById(1);
            if (usageStatus.getErrorCount() == null) {
                usageStatus.setErrorCount(1);
            } else {
                usageStatus.setErrorCount(usageStatus.getErrorCount() + 1);
            }
            reportRepository.save(usageStatus);
            return "News filed  to add";
        }
        finally {
            long endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;

            ReportTable usageStatus = reportRepository.getById(1);

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
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/updateNews", method = RequestMethod.POST)
    public String updateCustomer(@RequestBody NewsRequestForUpdate newsRequestForUpdate)throws ParseException  {
        long startTime = System.currentTimeMillis();
        String endPoint="NewsManagement";
        boolean isSuccess = false;
        try {
            newsServices.updateNewNews(newsRequestForUpdate);
            isSuccess = true;
            return " News Information updated Successfully ";
        } catch (Exception e) {
            ReportTable usageStatus = reportRepository.getById(1);
            if (usageStatus.getErrorCount() == null) {
                usageStatus.setErrorCount(1);
            } else {
                usageStatus.setErrorCount(usageStatus.getErrorCount() + 1);
            }
            reportRepository.save(usageStatus);
            return "update News Information  failed";
        }
        finally {
            long endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;

            ReportTable usageStatus = reportRepository.getById(1);

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
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/deleteNews", method = RequestMethod.GET)
    public String deletePolicy(Integer id) {
        long startTime = System.currentTimeMillis();
        boolean isSuccess = false;
        try {
            newsServices.deleteNews(id);
            return "Policy deleted Successfully ";
        } catch (Exception e) {
            ReportTable usageStatus = reportRepository.getById(1);
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

            ReportTable usageStatus = reportRepository.getById(1);

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
    @GetMapping
    public ResponseEntity<List<News>> searchNews(
            @RequestParam(value = "countryName", required = false) String countryName,
            @RequestParam(value = "regionName", required = false) String regionName,
            @RequestParam(value = "topicOfNews", required = false) String topicOfNews
    ) {
        long startTime = System.currentTimeMillis();
        boolean isSuccess = false;
        try {
            List<News> policies = newsServices.searchNews(countryName, regionName, topicOfNews);
            return ResponseEntity.ok(policies);
        } catch (Exception e) {
            ReportTable usageStatus = reportRepository.getById(1);
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

            ReportTable usageStatus = reportRepository.getById(1);

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

   // @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/allNews")
    public ResponseEntity<String> getAllNews() {
        long startTime = System.currentTimeMillis();
        boolean isSuccess = false;
        try {
            String newsAsString = newsServices.getAllNews();
            return ResponseEntity.ok(newsAsString);
        } catch (Exception e) {
            ReportTable usageStatus = reportRepository.getById(1);
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

            ReportTable usageStatus = reportRepository.getById(1);

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
    public ResponseEntity<News> getNewsById(@PathVariable("id") Integer id) {
        long startTime = System.currentTimeMillis();
        boolean isSuccess = false;
        try {
            isSuccess = true;
            News news = newsServices.getNewsById(id);
            return ResponseEntity.ok(news);
        } catch (Exception e) {
            ReportTable usageStatus = reportRepository.getById(1);
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

            ReportTable usageStatus = reportRepository.getById(1);

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
