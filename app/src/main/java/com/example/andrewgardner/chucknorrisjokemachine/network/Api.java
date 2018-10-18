package com.example.andrewgardner.chucknorrisjokemachine.network;

import com.example.andrewgardner.chucknorrisjokemachine.model.UsersList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("users?q=rokano")
    Call<UsersList> getUsers();
}
