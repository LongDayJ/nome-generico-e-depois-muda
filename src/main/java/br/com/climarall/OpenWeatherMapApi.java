package br.com.climarall;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class OpenWeatherMapApi {
    public static void print(Object x) {
        System.out.println(x);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String APIkey = "0c09765e328574bfbec932547d0f39fd";

        Scanner scanner = new Scanner(System.in);
        print("Digite sua cidade:");
        String search = scanner.nextLine();

        String forecastCall = "https://api.openweathermap.org/data/2.5/forecast?q="+search+"&lang=pt_br&appid="+APIkey;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(forecastCall))
          .build();

        HttpResponse<String> response = client
                .send(request, BodyHandlers.ofString());


        String json = response.body();

        JSONObject jsonObject = new JSONObject(json);
        JSONArray listArray = jsonObject.getJSONArray("list"); 
        JSONObject cityObject = jsonObject.getJSONObject("city");

        Weather[] forecastDays = new Weather[5];


        for (int i = 0; i < listArray.length(); i++) {
            JSONObject listItem = listArray.getJSONObject(i);
            JSONArray weatherArray = listItem.getJSONArray("weather");
            JSONObject weatherObject = weatherArray.getJSONObject(0);

            Weather weatherForecast = new Weather(
                    cityObject.getString("name"),
                    weatherObject.getString("main"),
                    weatherObject.getString("description"),
                    listItem.getString("dt_txt"));

            if (i == 0) {
                forecastDays[i] = weatherForecast;
            }
            else if (i != 0 &&
            weatherForecast.getTimer().contains(forecastDays[0].getTimer().split(" ")[1])) {
                forecastDays[i/8] = weatherForecast;
            }
            
        }
        
        for (Weather i : forecastDays) {
            print("");
            print(i);
        }

        scanner.close();
    }
    
}
