package com.lambda.FindMyGym.crawler;

import lombok.AllArgsConstructor;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor

@Component
public class fit4lessCrawler {

    public void getFit4lessDetails(String provinceName, String cityName) {
        //System.out.println("Crawling Fit4Less.....");
        try {
            //Chrome Drive initialization
            WebDriver driver = new ChromeDriver();

            //URL to navigate to
            driver.get("https://www.fit4less.ca/locations");

            //Setting an implicit wait so the driver can wait for the elements that have to be found
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            //Maximizing the browser window
            driver.manage().window().maximize();

            //Province Dropdown Selection and waiting for the list to open
            driver.findElement(By.id("province-dropdown")).click();
            Thread.sleep(1000);

            //Finding the user provided Province and selecting it
            WebElement dropdownElement = driver.findElement(By.cssSelector("#province-dropdown > ul > li[data-provname="+provinceName+"]"));
            Actions actions = new Actions(driver);
            actions.scrollToElement(dropdownElement).perform();
            dropdownElement.click();

            //Finding the user provided City and selecting it
            driver.findElement(By.id("city-dropdown")).click();
            Thread.sleep(1000);
            WebElement cityElement = driver.findElement(By.cssSelector("#city-dropdown > ul > li[data-cityname="+cityName+"]"));
            actions.scrollToElement(cityElement).perform();
            cityElement.click();

            //Clicking the "Find" Button
            driver.findElement(By.xpath("//*[@id=\"btn-find-your-gym\"]")).click();
            Thread.sleep(3000);

            //Array List to store the links of branches for that location
            List<String> availableGymLinks = new ArrayList<>();

            //From the available gyms, the links will be extracted and stored in the array list
            List<WebElement> availableGyms = driver.findElements(By.className("find-gym__result"));
            availableGyms.forEach(x -> {
                String GymLink = x.findElement(By.tagName("a")).getAttribute("href");
                availableGymLinks.add(GymLink);
            });

            System.out.println("Fit4Less Available Gyms: "+availableGymLinks);

            //HTML files will be stored in the respected folder
            String folderPath = "HTMLFilesfit4Less";

            //If folder is not present will simply create it
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            //Iterate over the links in the arraylist, access the page source and store it in the folder
            for(String i: availableGymLinks){
                try{
                    driver.get(i);
                    Thread.sleep(5000);
                    System.out.println("Crawling: " + i);
                    String pageSource = driver.getPageSource();
                    String lastPath = i.substring(i.lastIndexOf('/') + 1);
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(folderPath+"/"+"fit4less-"+lastPath+".html"));
                        writer.write(pageSource);
                        writer.close();
                    } catch (IOException e) {
                        //throw new RuntimeException(e);
                        System.out.println("Unable to download HTML files. Please try again.");
                    }
                }catch (NoSuchElementException e) {
                    // Handle NoSuchElementException when finding elements
                    //throw new RuntimeException("NoSuchElementException occurred: " + e.getMessage(), e);
                    System.out.println("Unable to locate elements. Please try again.");
                } catch (TimeoutException e) {
                    // Handle TimeoutException when WebDriverWait times out
                    //throw new RuntimeException("TimeoutException occurred: " + e.getMessage(), e);
                    System.out.println("Operation Timed out. Please try again.");
                } catch (WebDriverException e) {
                    // Handle WebDriverException when navigating to URL
                    //throw new RuntimeException("WebDriverException occurred: " + e.getMessage(), e);
                    System.out.println("An unexpected error occurred while executing the WebDriver. Please try again.");
                } catch (InterruptedException e) {
                    // Handle InterruptedException when sleeping
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    //throw new RuntimeException("InterruptedException occurred: " + e.getMessage(), e);
                    System.out.println("The execution of the WebDriver commands was interrupted. Please try again.");
                }

            }
            System.out.println("Crawling Fit4Less completed.");
            driver.close();
        } catch (NoSuchElementException e) {
            // Handle NoSuchElementException when finding elements
            //throw new RuntimeException("NoSuchElementException occurred: " + e.getMessage(), e);
            System.out.println("Unable to locate elements. Please try again.");
        } catch (TimeoutException e) {
            // Handle TimeoutException when WebDriverWait times out
            //throw new RuntimeException("TimeoutException occurred: " + e.getMessage(), e);
            System.out.println("Operation Timed out. Please try again.");
        } catch (WebDriverException e) {
            // Handle WebDriverException when navigating to URL
            //throw new RuntimeException("WebDriverException occurred: " + e.getMessage(), e);
            System.out.println("An unexpected error occurred while executing the WebDriver. Please try again.");
        } catch (InterruptedException e) {
            // Handle InterruptedException when sleeping
            Thread.currentThread().interrupt(); // Restore interrupted status
            //throw new RuntimeException("InterruptedException occurred: " + e.getMessage(), e);
            System.out.println("The execution of the WebDriver commands was interrupted. Please try again.");
        }


    }

}
