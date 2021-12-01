package at.lbg.dhp.sharedachievementbackend.data.dto;

import at.lbg.dhp.sharedachievementbackend.data.models.Challenge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubChallengeDTO {

    private String name;
    private int steps;
    private String challengeName;
}
