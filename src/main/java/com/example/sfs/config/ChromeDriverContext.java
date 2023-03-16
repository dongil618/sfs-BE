package com.example.sfs.config;

import com.example.sfs.util.PropertiesLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class ChromeDriverContext {

    private WebDriver driver;

    @Bean
    public WebDriver setupChromeDriver() throws IOException {
        Properties properties = PropertiesLoader.fromResource("application-secret.properties");
        String userAgent = properties.getProperty("SELENIUM_USER_AGENT");

        // 크롬 드라이버 자동 설치
        WebDriverManager.chromedriver().setup();

        // 드라이버 셋업
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");          // 최대크기로
        options.addArguments("--headless");                 // Browser를 띄우지 않음
        options.addArguments("--disable-gpu");              // GPU를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
        options.addArguments("--no-sandbox");               // Sandbox 프로세스를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
        options.addArguments("--disable-default-apps");     // 기본앱 사용안함
        options.addArguments("user-agent="+userAgent);  // user-agent 설정
        options.addArguments("window-size=1280x832");  // window 화면 크기 설정
        options.addArguments("lang=ko_KR");

        driver = new ChromeDriver(options);

        return driver;
    }
}
