package br.com.climarall;

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
    String cityName;
    String clima;
    String description;
    String timer;

    @Override
    public String toString() {
        return "Nome da cidade: " + cityName +" ,\n" +
                "O Clima está: "+ clima + " ,\n"+
                "Descrição: "+ description+" ,\n"+
                "Dia e Hora medida: "+timer;
    }
}
