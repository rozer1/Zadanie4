package com.example.demo;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
public class URLs {
    private List<Employee> empls;

    public List<Employee> generate(java.net.URL url) {
        empls = new ArrayList<>();
        HttpURLConnection connection;
        String inputLine;
        Employee empl = null;
        String Line;
        int id = 0;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("/user/profile.php?id=") && !inputLine.contains("img")) {
                    empl = new Employee();
                    inputLine = inputLine.replace(inputLine.substring(inputLine.indexOf("<a"), inputLine.indexOf("title")), "");
                    Line = inputLine.substring(inputLine.indexOf("<h3>") + 4, inputLine.indexOf("</a>"));
                    empl.Name(Line.substring(0, Line.indexOf(" ")));
                    empl.SurName(Line.substring(Line.indexOf(" ") + 1));
                    if (!inputLine.contains("nbsp"))
                        empl.setTitle(inputLine.substring(inputLine.indexOf("h4") + 3, inputLine.indexOf(" </h4>")));
                }
                if (inputLine.contains("Afiliacja")) {
                    Line = in.readLine();
                    if (empl != null) {
                        empl.setInstitute(Line.substring(Line.indexOf("content") + 9, Line.indexOf("</span>")));
                        empl.setId(id++);
                    }
                    empls.add(empl);
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return empls;
    }
}
