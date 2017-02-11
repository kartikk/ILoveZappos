package com.kartikk.zappos.ilovezappos.util;

import com.kartikk.zappos.ilovezappos.models.ZapposModel;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kartikk on 2/7/2017.
 */

public interface Endpoints {
    @GET("/Search")
    Call<ZapposModel> search(@Query("term") String term, @Query("key") String key);
}