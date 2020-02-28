/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.http.client.fluent.Request;


/**
 *
 * @author mluukkai
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
                
        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        List<Player> pelaajat = new ArrayList<>();
        for (Player player : players) {
            pelaajat.add(player);
        }
        List<Player> suomalaisetPelaajat = new ArrayList<>();
        Predicate<Player> byNationality = player -> player.getNationality().equals("FIN");
        suomalaisetPelaajat = pelaajat.stream().filter(byNationality).collect(Collectors.toList());
        
        ArrayList<Player> pelaajatJarjestyksessa = new ArrayList<>();
        
        for (Player player : suomalaisetPelaajat) {
            player.setPoints();
            pelaajatJarjestyksessa.add(player);
        }
        
        List<Player> suomalaisetPelaajatJarjestyksessa = new ArrayList<>();
        suomalaisetPelaajatJarjestyksessa = pelaajatJarjestyksessa.stream().sorted((Player p1, Player p2) -> {
            return p2.getPoints().compareTo(p1.getPoints());
        }).collect(Collectors.toList());
        
        System.out.println("Oliot:");
        for (Player player : suomalaisetPelaajatJarjestyksessa) {
            System.out.println(player);
        }   
    }
}
