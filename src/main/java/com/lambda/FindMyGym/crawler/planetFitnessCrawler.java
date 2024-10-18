package com.lambda.FindMyGym.crawler;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Component
public class planetFitnessCrawler {

    public void getPlanetFitnessDetails(String provinceName, String cityName) {
        try{
            //System.out.println("Crawling Planet Fitness.....");
            //ChromeOptions options = new ChromeOptions();

            //Chrome Drive initialization with Options
            WebDriver driver = new ChromeDriver();

            //URL to navigate to
            driver.get("https://www.planetfitness.ca/");

            //Setting an implicit wait so the driver can wait for the elements that have to be found
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

            //Finding input and adding user province and city name
            //driver.findElement(By.className("MuiInputBase-input")).sendKeys(cityName + ", " + provinceName);
            driver.findElement(By.xpath("/html/body/main/section[1]/div[1]/div[2]/div/div/div/form/input")).sendKeys(cityName + ", " + provinceName);
            //driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div[1]/div[4]/div/form/div/button")).click();
            driver.findElement(By.xpath("/html/body/main/section[1]/div[1]/div[2]/div/div/div/form/button")).click();

            //Array List to store the links of branches for that location
            List<String> availableGymLinks = new ArrayList<>();

            //From the available gyms, the links will be extracted and stored in the array list
            //WebElement parentDiv = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div"));
            WebElement parentDiv = driver.findElement(By.xpath("/html/body/main/div/div[2]/div[2]/div"));
            List<WebElement> availableGym = parentDiv.findElements(By.tagName("a"));
            for (WebElement i : availableGym) {
                String href = i.getAttribute("href");
                if (!href.endsWith("offers")) {
                    availableGymLinks.add(href);
                }
            }

            System.out.println("Planet Fitness Available Gyms: " + availableGymLinks);

            //HTML files will be stored in the respected folder
            String folderPath = "HTMLFilesPlanetFitness";

            //If folder is not present will simply create it
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            //Iterate over the links in the arraylist, access the page source and store it in the folder
            for (String i : availableGymLinks) {
                //Initializing a new web driver as cloud front is throwing error of "Are you a human?"
                WebDriver driver1 = new ChromeDriver();
                driver1.get(i);
                driver1.manage().window().maximize();
                Thread.sleep(5000);
                System.out.println("Crawling: " + i);

                // Check if the popup appears and closing it
                try {
                    WebElement popupElement = driver1.findElement(By.xpath("/html/body/div[2]/div[3]/div"));
                    //System.out.println("Popper Found: " + i);

                    //Closing popup if found
                    if (popupElement.isDisplayed()) {
                        WebElement closeButton = driver1.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[1]/div/button"));
                        closeButton.click();
                        System.out.println("Closed popup on URL: " + i);
                    }
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    // Popup not found, continue to next URL
                }


                Thread.sleep(3000);
                String pageSource = driver1.getPageSource();
                String lastPath = i.substring(i.lastIndexOf('/') + 1);
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(folderPath + "/" + "planetFitness-" + lastPath + ".html"));
                    writer.write(pageSource);
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error downlaoding HTML file. Please try again.");
                    //throw new RuntimeException(e);
                }
                driver1.quit();
            }

            System.out.println("Crawling Planet Fitness completed.");
            driver.quit(); //Closing the main driver
        }catch (NoSuchElementException e) {
            // Handle NoSuchElementException when finding elements
            //throw new RuntimeException("NoSuchElementException occurred: " + e.getMessage(), e);
            System.out.println("Unable to locate elements. Please try again.");
        }  catch (WebDriverException e) {
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
