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
        Weather weather = new Weather();

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
        JSONObject firstObject = listArray.getJSONObject(0);
        
        JSONObject cityObject = jsonObject.getJSONObject("city");

        JSONArray weatherArray = firstObject.getJSONArray("weather");
        JSONObject weatherObject = weatherArray.getJSONObject(0);


        weather.setCityName(cityObject.getString("name"));
        weather.setClima(weatherObject.getString("main"));
        weather.setDescription(weatherObject.getString("description"));
        weather.setTimer(firstObject.getString("dt_txt"));

        print("");
        print(weather);

        String[] horas = weather.getTimer().split(" ");
        print("");
        print(horas[1]);

        scanner.close();
    }
    
}
