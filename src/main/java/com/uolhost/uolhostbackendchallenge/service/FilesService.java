package com.uolhost.uolhostbackendchallenge.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class FilesService {

    public List<String> fetchAvengers() throws Exception {
        List<String> codenames = new ArrayList<>();

        String vingadores = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(vingadores))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<Map<String, String>>> jsonData = mapper.readValue(responseBody, new TypeReference<Map<String, List<Map<String, String>>>>() {
        });
        List<Map<String, String>> avengers = jsonData.get("vingadores");
        for (Map<String, String> map : avengers) {
            codenames.addAll(map.values());
        }
        return codenames;
    }

    public List<String> fetchLeague() throws Exception {
        String liga = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml";
        List<String> codenames = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(liga);
        NodeList codenameList = document.getElementsByTagName("codinome");
        for (int i = 0; i < codenameList.getLength(); i++) {
            Element element = (Element) codenameList.item(i);
            String codename = element.getTextContent();
            codenames.add(codename);
        }
        return codenames;
    }
}
