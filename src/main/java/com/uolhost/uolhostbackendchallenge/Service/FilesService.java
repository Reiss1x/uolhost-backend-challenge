package com.uolhost.uolhostbackendchallenge.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class FilesService {
    private String vingadores = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json";
    private String liga = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml";

    public List<String> fetchAvengers() {
        List<String> codenames = new ArrayList<>();
        try {
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
        } catch (Exception e){
            e.printStackTrace();
        }
        return codenames;
    }

    public List<String> fetchLeague() {
        List<String> codenames = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(liga);

            NodeList codenameList = document.getElementsByTagName("codename");

            for (int i = 0; i < codenameList.getLength(); i++) {
                Element element = (Element) codenameList.item(i);
                String codename = element.getTextContent();
                codenames.add(codename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codenames;
    }
}
