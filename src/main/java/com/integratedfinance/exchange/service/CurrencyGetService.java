package com.integratedfinance.exchange.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class CurrencyGetService {

    // essential URL structure is built using constants
    public static final String ACCESS_KEY = "fe7f20df759cedb97c19dc181cb6b5fe";
    public static final String BASE_URL = "http://api.currencylayer.com/";
    public static final String ENDPOINT = "live";
    public static final String LIST = "list";

    // this object is used for executing requests to the (REST) API
    static CloseableHttpClient httpClient = HttpClients.createDefault();


    public static JSONObject sendLiveRateRequest(String source){
        JSONObject exchangeRates = null;
        HttpGet get = null;
        if (source.equals("USD")) {
            get = new HttpGet(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY);
        } else {
            get = new HttpGet(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY + "&source=" + source);
        }

        try {
            CloseableHttpResponse response =  httpClient.execute(get);
            HttpEntity entity = response.getEntity();

            exchangeRates = new JSONObject(EntityUtils.toString(entity));
            response.close();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return exchangeRates;
    }

    public static JSONObject getCurrenciesRequest(){
        JSONObject exchangeRates = null;
        HttpGet get = new HttpGet(BASE_URL + LIST + "?access_key=" + ACCESS_KEY);

        try {
            CloseableHttpResponse response =  httpClient.execute(get);
            HttpEntity entity = response.getEntity();

            exchangeRates = new JSONObject(EntityUtils.toString(entity));
            response.close();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return exchangeRates;
    }

}
