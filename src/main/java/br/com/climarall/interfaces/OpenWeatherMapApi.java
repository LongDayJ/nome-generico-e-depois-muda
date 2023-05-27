package br.com.climarall.interfaces;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

import br.com.climarall.*;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OpenWeatherMapApi {
    public static void print(Object x) {
        System.out.println(x);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String APIkey = "0c09765e328574bfbec932547d0f39fd";
        weather weather = new weather();

        Scanner scanner = new Scanner(System.in);
        print("Digite sua cidade:");
        String search = scanner.nextLine();

        // TODO Lembrar de Mudar o Limite
        String forecastCall = "https://api.openweathermap.org/data/2.5/forecast?q="+search+"&lang=pt_br&appid="+APIkey;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(forecastCall))
          .build();
                     
        HttpResponse<String> response = client
                .send(request, BodyHandlers.ofString());


        String json = response.body();
        print(json);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        
        

        scanner.close();
    }
    
}
