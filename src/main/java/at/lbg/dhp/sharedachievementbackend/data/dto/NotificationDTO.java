package at.lbg.dhp.sharedachievementbackend.data.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
    
    private List<String> to;
    private String title;
    private String body;
}
