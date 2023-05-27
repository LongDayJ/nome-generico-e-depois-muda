package br.com.climarall;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Weather {
    String cityName;
    String cityCountry;
    String clima;
    String description;
    String timer;

    @Override
    public String toString() {
        return "Nome da cidade: " + cityName + ",\n" +
                "O pais dela: " + cityCountry + "\n"+
                "O Clima está: "+ clima + ",\n"+
                "Descrição: "+ description+",\n"+
                "Dia e Hora medida: "+timer;
    }
}
