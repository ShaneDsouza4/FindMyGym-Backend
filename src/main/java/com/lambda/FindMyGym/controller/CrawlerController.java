package com.lambda.FindMyGym.controller;

import com.lambda.FindMyGym.crawler.fit4lessCrawler;
import com.lambda.FindMyGym.crawler.planetFitnessCrawler;
import com.lambda.FindMyGym.model.Task;
import com.lambda.FindMyGym.parser.fit4lessParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CrawlerController {

    @Autowired
    private fit4lessCrawler  fit4lessCrawler  = new fit4lessCrawler();

    @Autowired
    private planetFitnessCrawler planetFitnessCrawler = new planetFitnessCrawler();

    @Autowired
    fit4lessParser fit4lessParser = new fit4lessParser();


    @GetMapping("/fit4lessCrawl/{province}/{city}")
    public String fit4lessCrawl(@PathVariable String province, @PathVariable String city) throws InterruptedException, IOException {
        fit4lessCrawler.getFit4lessDetails(province, city);
        fit4lessParser.parsefit4Less();
        return "Fit4less Crawl Completed for " + province + ", " + city;
    }

    @GetMapping("/planetFitnessCrawl/{province}/{city}")
    public String planetFitnessCrawl(@PathVariable String province, @PathVariable String city) throws InterruptedException, IOException {
        planetFitnessCrawler.getPlanetFitnessDetails(province, city);
        //fit4lessCrawler.getFit4lessDetails(province, city);
        //fit4lessParser.parsefit4Less();
        return "Fit4less Crawl Completed for " + province + ", " + city;
    }
}
