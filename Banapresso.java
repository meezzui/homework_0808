package com.koreait.crawling;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Banapresso {

	public static void main(String[] args) {
		String DRIVER_ID = "webdriver.chrome.driver";
		String DRIVER_PATH = "C:/JSP/chromedriver.exe";
		
		System.setProperty(DRIVER_ID, DRIVER_PATH);
		WebDriver driver = new ChromeDriver();
		
		String base_url = "https://www.banapresso.com/store";
		
		try {
			driver.get(base_url);
			Thread.sleep(1000);
			
			List<WebElement> elements = driver.findElements(By.className("store_name_map"));
			System.out.println("******바나프레소 지점명/주소******");
			for(WebElement el : elements) {
				System.out.println("지점명 : " + el.findElement(By.tagName("i")).getText());
				System.out.println("주소 : "+ el.findElement(By.tagName("span")).getText());
			}
			int i = 2;
			while(true) {
				try {
					WebElement Nextpage = driver.findElement(By.cssSelector("div.pagination > ul > li:nth-child("+i+")"));
					Nextpage.click();
					Thread.sleep(1000);
					
					elements = driver.findElements(By.className("store_name_map"));
					System.out.println("******바나프레소 지점명/주소******");
					for(WebElement el : elements) {
						System.out.println("지점명 : " + el.findElement(By.tagName("i")).getText());
						System.out.println("주소 : "+ el.findElement(By.tagName("span")).getText());
					}
					if(i<5) {
						i++;
					} else if(i==5) {
						WebElement Nextpage1 = driver.findElement(By.cssSelector(".pagination > span > a"));
						Nextpage1.click();
						i=1;
					}
				}catch(Exception e) {
					System.out.println("프로그렘 종료.");
					break;
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
