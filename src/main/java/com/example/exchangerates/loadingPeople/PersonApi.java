package com.example.exchangerates.loadingPeople;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

// todo сделано

public interface PersonApi {
    @GET("persons/all")
    Call<List<Person>> getPersons();
    /**
     * метод для получения всех людей, "лежащих в json - е на сервере"
     */
}