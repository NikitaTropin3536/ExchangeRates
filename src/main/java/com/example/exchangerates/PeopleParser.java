package com.example.exchangerates;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * класс для парсинга людей
 */
public class PeopleParser {

    /**
     * url сайта
     */
    private static final String url = "https://myrouble.ru/?s=биография+успеха";

    /**
     * метод для получения тега,
     * который содержит ссылки на страницы с информацией о людях
     */
    public void getTag() {

        /** получаем полный Document */
        Document document = getFullDocument(url);

        /**
         * почередно получаем содержимое тегов
         * и поочередно проваливаемся внутрь,
         * пока не найдем нужный тег
         */
        Elements divTags = document.select("body > div");
        Elements vceMain = divTags.get(1).getAllElements();
        Elements mainWrapper = vceMain.select("div#main-wrapper");
        Elements content = mainWrapper.select("div#content");
        Elements primary = content.select("div#primary");
        Elements main_box = primary.select("div.main-box");
        Elements main_box_inside = main_box.select("div.main-box-inside");

        /** этот тег содержит нужную нам информацию */
        Elements vce_loop_wrap = main_box_inside.select("div.vce-loop-wrap");

        for (Element element : vce_loop_wrap.select("article.vce-post")) {
            /** выводим всех людей */
            Log.d("Human: ", element.text());
        }
    }

    /**
     * метод, в котором содержится вся логика нажатия на кнопку
     * "Показать еще"
     */
    private Document getFullDocument(String url) {

        /** Указываем путь к драйверу Chrome */
        System.setProperty("web-driver.chrome.driver",
                "libs/chromedriver.exe");

        Log.d("Chrome Driver", "GOOD");

        /** Создаем объект веб-драйвера */
        WebDriver driver = new ChromeDriver();

        /** Открываем страницу */
        driver.get(url);
        Log.d("Open Page", "GOOD");

        /** Нажимаем на кнопку 2 раза */
        driver.findElement(By.id("vce-pagination")).click();
        driver.findElement(By.id("vce-pagination")).click();
        Log.d("ClickVcePaginationTwo", "GOOD");

        /** записываем всю информацию в объект типа Document */
        Document doc = Jsoup.parse(driver.getPageSource());

        // Закрываем браузер
        driver.quit();

        Log.d("End", "GOOD");
        /**
         * возвращаем документ со всей нужной нам информацией
         */
        return doc;
    }
}