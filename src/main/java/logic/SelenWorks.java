package logic;

import dto.Article;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SelenWorks {
    private static final Logger logger = Logger.getLogger(SelenWorks.class);

    private WebDriver driver;
    private WebDriverWait wait;

    public SelenWorks(){
        start();
    }

    /**
     * Get PageSource
     * @param list
     * @return
     */
    public  List<String> reqPages(List<Article> list){
        logger.info("SelenWorks, reqPages() start");
        List<String> listSelDoc = new ArrayList<>();
        Long startTimeAllreq = System.currentTimeMillis();
        list.forEach(li -> {
            boolean flagSelWorks = true;
                if (!li.getPeriud().equals("Перерыв")){
                    Integer  per = Integer.parseInt(li.getPeriud().substring(0, 1));
                    if (per == 4){ flagSelWorks = false;}
                }
            if (flagSelWorks){
            driver.navigate().to(li.getUrl());
            Long startTime = System.currentTimeMillis();
            try {
                driver.findElement(By.className("scoreboard__team-name"));
            } catch (NoSuchElementException el) {
                logger.error(el);
            } finally {
                logger.info("TimeReqest(reqPages()):" + ((float)(System.currentTimeMillis() - startTime)/1000) + " с.");
            }
            listSelDoc.add(driver.getPageSource());
            }
        });
        logger.info( "TimeReqestAll(reqPages()):" + (float)(System.currentTimeMillis() - startTimeAllreq)/1000 + " c.");
        return listSelDoc;
    }

    private void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void stop(){
        driver.quit();
        driver = null;
    }
}
