package com.example.cgv;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import javax.xml.xpath.XPath;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service

public class TheaterService extends Thread {
    boolean flag = true;
    String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMdd"));

    public String getDatePath() {
        return datePath;
    }

    public void setDatePath(String datePath) {
        this.datePath = datePath;
    }

    public void run() {
        String chromeDriver = "webdriver.chrome.driver"; // 크롬드라이버 명시
        String chromePath = "C:\\Users\\zidwk\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"; // 셀레니움 크롬 .exe 경로

        System.setProperty(chromeDriver, chromePath); // 위의 드라이버 셋팅

        //Driver SetUp ( 셀레니움을 막는 것을 방지 )
        ChromeOptions options = new ChromeOptions();
        options.setCapability("ignoreProtectedModeSettings", true);
        options.addArguments("--disable-application-cache");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-geolocation");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-speech-api");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-infobars");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver driver = new ChromeDriver();

        driver.get("https://class.mju.ac.kr/main?lang=ko"); // get을 통해 해당 url을 open
        while(flag){
            try {
                driver.navigate().refresh();
                WebElement element = driver.findElement(By.id("username"));
                element.click();
                element.sendKeys("60201665");
                element = driver.findElement(By.id("password"));
                element.click();
                element.sendKeys("Zidwkd00@");
                element = driver.findElement(By.className("languagehtml"));
                element.click();
                flag = false;
            } catch (Exception e) {
                driver.switchTo().defaultContent();
                driver.navigate().refresh();
                e.printStackTrace();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        flag = true;
        WebDriverWait wait = new WebDriverWait(driver,1);
        while (flag) {
            try {
                WebElement element = driver.findElement(By.id("req-6187"));
                element.click();

                // Alert이 나타날 때까지 대기
                wait.until(ExpectedConditions.alertIsPresent());

                // Alert 창 스위치
                Alert alert = driver.switchTo().alert();

                // Alert 수락
                alert.accept();

                // 추가로 어떤 동작을 수행하려면 여기에 작성합니다.

                // 다음 요소 클릭
                element = driver.findElement(By.id("req-6186"));
                element.click();

                // Alert이 나타날 때까지 대기
                wait.until(ExpectedConditions.alertIsPresent());

                // Alert 창 스위치
                alert = driver.switchTo().alert();

                // Alert 수락
                alert.accept();

                // 추가로 어떤 동작을 수행하려면 여기에 작성합니다.

            } catch (NoAlertPresentException e) {
                // 알림이 나타나지 않을 경우에 대한 처리
                // 예를 들어, 다음 요소를 클릭하거나 다른 동작을 수행할 수 있습니다.
                WebElement element = driver.findElement(By.id("req-6186"));
                element.click();

                // 추가로 어떤 동작을 수행하려면 여기에 작성합니다.

            } catch (Exception e) {
                // 기타 오류 발생 시 페이지 새로고침
                driver.navigate().refresh();

                // 잠시 대기
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    }

    public String changeDate(String newDate) {
        datePath = newDate;
        return datePath;

    }

    public String a() {

        return datePath;
    }

    public void end() {
        flag=true;
    }
}
