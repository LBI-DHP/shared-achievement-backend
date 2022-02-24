package at.lbg.dhp.sharedachievementbackend.service;


import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import at.lbg.dhp.sharedachievementbackend.data.dto.NotificationDTO;
import nonapi.io.github.classgraph.json.JSONSerializer;
import reactor.util.function.Tuple2;


@Service
public class NotificationService {
    
    @Autowired
    PersonService personService;

    static final String NOTIFICATION_URL = "https://exp.host/--/api/v2/push/send";


    public enum MessageType{
        CHALLENGE_ACCOMPLISHED,
        CONTRIBUTE_STEPS,
        NEW_USER_JOINED_TEAM,
        
    }


    public static NotificationDTO createMessage(MessageType type, String personId, String... bodyParams){
        NotificationDTO notification = new NotificationDTO();
        String title = "👻";
        String body = "🧟";
        switch(type){
        case CHALLENGE_ACCOMPLISHED:
            notification.setTitle("Challenge Accomplished 🎉");
            String body = String.format("Congratulations!\nTeam %s completed the challenge.", bodyParams[0]);
            notification.setBody(body);
            break;
        case CONTRIBUTE_STEPS:
        notification.setTitle("New Cteps Contributed 👣");
        String body = String.format("%s contributed %d steps.", bodyParams[0], bodyParams[1]);
        notification.setBody(body);
            break;
        }
        return notification;
    }

    public HttpStatus sendMessage(NotificationDTO notificationDTO) {
        String json = JSONSerializer.serializeObject(notificationDTO);

        // byte[] message = json.getBytes(StandardCharsets.UTF_8);
        // int msgLength = message.length;
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(NOTIFICATION_URL))
        .timeout(Duration.ofMinutes(2))
        .header("Content-Type", "application/json")
        .POST(BodyPublishers.ofString(json))
        .build();
        HttpResponse<String> response;
        HttpStatus result = HttpStatus.INTERNAL_SERVER_ERROR;
        
        try {
            response = client.send(request, BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body()); 
            result = (HttpStatus.valueOf(response.statusCode()));
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }
    public HttpStatus sendMessageToPerson(String personId, String title, String body) {
        // HttpClient http = HttpClient.newHttpClient();
        // HttpRequest request = HttpRequest()
        // http.se

        String token = personService.getPerson(personId).getExpoToken();
        NotificationDTO notification = new NotificationDTO(Arrays.asList(token), title, body);
        
        return sendMessage(notification);
        
    }

    public HttpStatus sendMessageToTeam(String teamId, List<String> excludePersons, String title, String body){

    }

}
