package com.example.exchangerates.loadingPeople;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.exchangerates.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// TODO ПРОВЕРЕНО И СДЕЛАНО

/* загрузчик людей */
public class LoaderPersons {

    private final Context context;

    private final Handler handler;

    /* список людей */
    private List<Person> persons = new ArrayList<>();

    /* имена людей */
    private List<String> personNames = new ArrayList<>();

    /* фотки */
    private List<String> personPhotos = new ArrayList<>();

    /* что люди сделали? */
    private List<String> personWhatDo = new ArrayList<>();

    /* предисловия */
    private List<String> personSummaries = new ArrayList<>();

    /* ссылки на источники с полными биографиями */
    private List<String> infoLinks = new ArrayList<>();

    /* ссылки на источники с дополнительной информацией */
    private List<String> dopLinks = new ArrayList<>();

    public LoaderPersons(Context context,
                         Handler handler) {
        this.context = context;
        this.handler = handler;

        /* получаем наших людей */
        loadPeopleFromServer(persons);
    }

    /**
     * метод для получения людей из json
     */
    public void loadPeopleFromServer(List<Person> persons) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PersonApi personsApi = retrofit.create(PersonApi.class);

        Call<List<Person>> call = personsApi.getPersons();

        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call,
                                   Response<List<Person>> response) {

                if (!response.isSuccessful()) {
                    try {
                        Log.v("LoadData", response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Toast.makeText(context, "Не достучаться до сервера",
                            Toast.LENGTH_SHORT).show();
                }

                List<Person> personList = response.body();
                persons.addAll(personList); /* добавляем людей в список*/

                /* заполняем данными списки имен, фоток и так далее */
                loadPersonsData(persons, personNames, personPhotos,
                        personWhatDo, personSummaries,
                        infoLinks, dopLinks);

                /* отправляем handler - у сообщение - мы закончили данными */
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(Call<List<Person>> call,
                                  Throwable t) {
                Log.v("LoadData", t.getMessage());
                Toast.makeText(context, "Произошла ошибка",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* метод для загрузки данных этих людей в соответствующие списки */
    public void loadPersonsData(List<Person> persons,
                                List<String> personNames,
                                List<String> personPhotos,
                                List<String> personWhatDo,
                                List<String> personSummaries,
                                List<String> infoLinks,
                                List<String> dopLinks) {
        /* забираем данные */
        takingNamesFromPersons(persons, personNames);
        takingPhotosFromPersons(persons, personPhotos);
        takingWhatDoFromPersons(persons, personWhatDo);
        takingSummariesFromPersons(persons, personSummaries);
        takingInfoLinksFromPersons(persons, infoLinks);
        takingDopLinksFromPersons(persons, dopLinks);
    }

    /**
     * метод для заполнения списка имен
     */
    private void takingNamesFromPersons(List<Person> persons,
                                        List<String> personNames) {
        for (Person person : persons) {
            personNames.add(person.name());
        }
    }

    /**
     * метод для заполнения списка фотографий
     */
    private void takingPhotosFromPersons(List<Person> persons,
                                         List<String> personPhotos) {
        for (Person person : persons) {
            personPhotos.add(person.photo());
        }
    }

    /**
     * метод для заполнения списка достижений
     */
    private void takingWhatDoFromPersons(List<Person> persons,
                                         List<String> personWhatDo) {
        for (Person person : persons) {
            personWhatDo.add(person.whatDo());
        }
    }

    /**
     * метод для заполнения списка предисловий
     */
    private void takingSummariesFromPersons(List<Person> persons,
                                            List<String> personSummaries) {
        for (Person person : persons) {
            personSummaries.add(person.summary());
        }
    }

    /**
     * метод для заполнения списка источников с биографиями
     */
    private void takingInfoLinksFromPersons(List<Person> persons,
                                            List<String> infoLinks) {
        for (Person person : persons) {
            infoLinks.add(person.infoLink());
        }
    }

    /**
     * метод для заполнения списка источников с дополнительной информацией
     */
    private void takingDopLinksFromPersons(List<Person> persons,
                                           List<String> dopLinks) {
        for (Person person : persons) {
            dopLinks.add(person.dopLink());
        }
    }

    public List<Person> persons() {
        return persons;
    }

    public List<String> personNames() {
        return personNames;
    }

    public List<String> personPhotos() {
        return personPhotos;
    }

    public List<String> personWhatDo() {
        return personWhatDo;
    }

    public List<String> personSummaries() {
        return personSummaries;
    }

    public List<String> infoLinks() {
        return infoLinks;
    }

    public List<String> dopLinks() {
        return dopLinks;
    }
}