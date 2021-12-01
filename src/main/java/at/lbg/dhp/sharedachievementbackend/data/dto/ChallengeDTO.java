package at.lbg.dhp.sharedachievementbackend.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeDTO {

    private String name;
    private int steps;
}
