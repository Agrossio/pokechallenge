package com.jemersoft.pokechallenge.config;

import com.jemersoft.pokechallenge.model.response.myresponse.MyPokemonListResponse;
import com.jemersoft.pokechallenge.model.response.myresponse.MyPokemonResponse;
import lombok.Data;

import java.util.List;

public class Cache {
    private static List<MyPokemonListResponse> cachedListResponse;
    private static List<String> queryParametersList;
    private static MyPokemonResponse cachedResponse;
    private static List<String> queryParameters;

    public static List<MyPokemonListResponse> getCachedListResponse() {
        return cachedListResponse;
    }

    public static void setCachedListResponse(List<MyPokemonListResponse> cachedListResponse) {
        Cache.cachedListResponse = cachedListResponse;
    }

    public static List<String> getQueryParametersList() {
        return queryParametersList;
    }

    public static void setQueryParametersList(List<String> queryParametersList) {
        Cache.queryParametersList = queryParametersList;
    }

    public static MyPokemonResponse getCachedResponse() {
        return cachedResponse;
    }

    public static void setCachedResponse(MyPokemonResponse cachedResponse) {
        Cache.cachedResponse = cachedResponse;
    }

    public static List<String> getQueryParameters() {
        return queryParameters;
    }

    public static void setQueryParameters(List<String> queryParameters) {
        Cache.queryParameters = queryParameters;
    }
}
