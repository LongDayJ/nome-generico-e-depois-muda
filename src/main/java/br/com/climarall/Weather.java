package br.com.climarall;

import com.google.gson.annotations.SerializedName;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Weather {

    @SerializedName("city.name")
    String cityName;

    @SerializedName("main")
    String clima;

    @SerializedName("description")
    String description;

    @SerializedName("list.dt_txt")
    String timer;

    @Override
    public String toString() {
        return "Nome da cidade: " + cityName +" ,\n" +
                "O Clima está: "+ clima + " ,\n"+
                "Descrição: "+ description+" ,\n"+
                "Dia e Hora medida: "+timer;
    }
}
