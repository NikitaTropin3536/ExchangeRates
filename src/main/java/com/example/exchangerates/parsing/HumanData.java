package com.example.exchangerates.parsing;

import org.jsoup.nodes.Document;

// TODO ПРОВЕРЕНО И СДЕЛАНО

/* данные человека */
public class HumanData {

    /**
     * имя -
     * (ставим только в тех случаях,
     * когда whatLink = 4)
     */
    private String name;

    /* что это за страничка и есть ли она вообще?
     * 1 - мой рубль,
     * 2 - dzen.ru,
     * 3 - vc.ru,
     * 4 - отсутствует
     */
    private int whatLink;

    /* содержимое странички (если страничка присутствует) */
    private Document document;

    public HumanData(int whatLink) {
        this.whatLink = whatLink;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String name() {
        return name;
    }

    public int whatLink() {
        return whatLink;
    }

    public Document document() {
        return document;
    }
}