package com.example.exchangerates.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// TODO ПРОВЕРЕНО И СДЕЛАНО

/**
 * класс для парсинга биографии человека со странички
 * и получения этой биографии
 */
public class HumanBiographyParser {

    /* имена людей */
    private List<String> personNames = new ArrayList<>();

    /* ссылки на источники с полными биографиями */
    private List<String> infoLinks = new ArrayList<>();

    public HumanBiographyParser(List<String> personNames,
                                List<String> infoLinks) {
        this.personNames = personNames;
        this.infoLinks = infoLinks;
    }

    /* получение данных о человеке */
    public HumanData getHumanData(int position) throws IOException {

        /* берем страничку с биографией человека */
        String biography = infoLinks.get(position);

        int whatLink; /* что это за страничка и есть ли она вообще?*/

        if (biography.startsWith("https://myrouble.ru/")) {
            whatLink = 1; /* мой рубль */
        } else if(biography.startsWith("https://dzen.ru/")) {
            whatLink = 2; /* https://dzen.ru/ */
        } else if(biography.startsWith("https://vc.ru/")) {
            whatLink = 3; /* vc.ru */
        } else whatLink = 4; /* отсутствует */

        if (whatLink / 4 == 1) {
            /* если нет ссылки на страничку с биографией */

            HumanData humanData = new HumanData(whatLink);

            /* устанавливаем имя */
            humanData.setName(personNames.get(position));
            return humanData;
        }

        /* если же ссылка на страничку с биографией есть... */

        Document document;

        /** сохраняем все содержимое странички в объект типа Document */
        document = Jsoup.connect(biography).get();

        HumanData humanData = new HumanData(whatLink);
        humanData.setDocument(document);

        return humanData;
    }

    /* метод для получения биографии */
    public String getBiography(HumanData humanData) {

        String biography = "";

        switch (humanData.whatLink()) {
            case 1:
                biography = getBiographyFromMyRouble(humanData);
                break;
            case 2:
                biography = getBiographyFromDzen(humanData);;
                break;
            case 3:
                biography = getBiographyFromVc(humanData);
                break;
            case 4:
                biography = getBiographyMyFriend(humanData);
                break;
        }
        return biography;
    }

    /* получение биографии с https://myrouble.ru/ */
    private String getBiographyFromMyRouble(HumanData couple) {

        /* здесь будет хранится вся биография */
        StringBuilder builder = new StringBuilder();

        /** сохраняем все содержимое странички */
        Document document = couple.document();

        Elements biography = document
                .select("div.entry-content > p, h1, h2, h3");

        for (Element element : biography) {
            if (!element.text().equals("Список источников")) {

                if (!element.text().equals("")) {/* если element не содержит пустую строку */

                    builder.append("\n")
                            .append(element.text())
                            .append("\n");
                }
            }
            else if (!element.text().equals("Еще по теме")) break; /* завершаем биографию */
        }
        return builder.toString();
    }

    /**
     *  получение биографии с https://vc.ru/
     *  (получение биографии Стива Возняка)
     */
    private String getBiographyFromDzen(HumanData couple) {

        /* здесь будет хранится вся биография */
        StringBuilder builder = new StringBuilder();

        /** сохраняем все содержимое странички */
        Document document = couple.document();

//        Elements biography = document
//                .select("div.publication-columns-layout__middle");
//
//        for (Element element : biography) {
//            if (!element.text().equals("")) {/* если element не содержит пустую строку */
//
//                builder.append("\n")
//                        .append(element.text())
//                        .append("\n");
//            }
//            else if (!element.text().equals("Понравилось?")) break; /* завершаем биографию */
//        }
//        return builder.toString();
        builder.append("Стив Возняк");
        return builder.toString();
    }

    /**
     * получение биографии с https://dzen.ru/
     * (получение биографии Говарда Шульца)
     * */
    private String getBiographyFromVc(HumanData couple) {

        /* здесь будет хранится вся биография */
        StringBuilder builder = new StringBuilder();

        /** сохраняем все содержимое странички */
        Document document = couple.document();

        Elements biography = document
                .select("div.content content--full > div.l-island-a, h2");

        for (Element element : biography) {
            if (!element.text().equals("")) {/* если element не содержит пустую строку */

                builder.append("\n")
                        .append(element.text())
                        .append("\n");
            }
            else if (!element.text().equals("Понравилось?")) break; /* завершаем биографию */
        }
        return builder.toString();
    }

    /* получение биографий моих друзей */
    private String getBiographyMyFriend(HumanData humanData) {

        String biography = "";

        String name = humanData.name(); /* получаем имя друга */

        switch (name) {
            case "Алексей Хлопков":
                biography = "ааа";
                break;
            case "Иван Мурзин":
                biography = "ааааааааааа";
                break;
            case "Максим Садков":
                biography = "аааааааа";
                break;
            case "Даниил Покровский":
                biography = "ааа";
                break;
            case "Арсений Аношин":
                biography = "ааааа";
                break;
            case "Александр Ошаров":
                biography = "аааа";
                break;
        }
        return biography;
    }

    public List<String> infoLinks() {
        return infoLinks;
    }
}