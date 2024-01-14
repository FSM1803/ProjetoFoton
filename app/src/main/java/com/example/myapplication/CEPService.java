package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {
    @GET("/ws/{cep}/json/")
    Call<CEPData> consultarCEP(@Path("cep") String cep);
}
