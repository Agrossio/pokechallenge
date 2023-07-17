package com.jemersoft.pokechallenge.config;

import com.jemersoft.pokechallenge.model.response.myresponse.MyPokemonListResponse;
import com.jemersoft.pokechallenge.model.response.myresponse.MyPokemonResponse;

import java.util.List;

public class Cache {
    private static List<MyPokemonListResponse> cachedListResponse;
    private static List<Integer> integerQueryParameters;
    private static MyPokemonResponse cachedResponse;
    private static List<String> stringQueryParameters;

    public static List<MyPokemonListResponse> getCachedListResponse() {
        return cachedListResponse;
    }

    public static void setCachedListResponse(List<MyPokemonListResponse> cachedListResponse) {
        Cache.cachedListResponse = cachedListResponse;
    }

    public static List<Integer> getIntegerQueryParameters() {
        return integerQueryParameters;
    }

    public static void setIntegerQueryParameters(List<Integer> integerQueryParameters) {
        Cache.integerQueryParameters = integerQueryParameters;
    }

    public static MyPokemonResponse getCachedResponse() {
        return cachedResponse;
    }

    public static void setCachedResponse(MyPokemonResponse cachedResponse) {
        Cache.cachedResponse = cachedResponse;
    }

    public static List<String> getStringQueryParameters() {
        return stringQueryParameters;
    }

    public static void setStringQueryParameters(List<String> stringQueryParameters) {
        Cache.stringQueryParameters = stringQueryParameters;
    }
}
