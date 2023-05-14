package com.example.exchangerates.loadingPeople;

// todo сделано

/* класс какой - то персоны / человека */
public class Person {

    private Integer id;

    private String name; /* имя */

    private String photo; /* фото */

    private String whatDo; /* что сделал? / чем известен? */

    /* предисловие */
    private String summary;

    /* ссылка на ресурс с полной биографией */
    private String infoLink;

    /* ссылка на доп. ресурс */
    private String dopLink;

    public Person(String name,
                  String photo,
                  String whatDo,
                  String summary,
                  String infoLink,
                  String dopLink) {
        this.name = name;
        this.photo = photo;
        this.whatDo = whatDo;
        this.summary = summary;
        this.infoLink = infoLink;
        this.dopLink = dopLink;
    }

    public String name() {
        return name;
    }

    public String photo() {
        return photo;
    }

    public String whatDo() {
        return whatDo;
    }

    public String summary() {
        return summary;
    }

    public String infoLink() {
        return infoLink;
    }

    public String dopLink() {
        return dopLink;
    }
}