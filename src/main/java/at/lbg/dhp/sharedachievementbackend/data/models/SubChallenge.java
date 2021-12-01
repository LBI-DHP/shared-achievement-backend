package at.lbg.dhp.sharedachievementbackend.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(SubChallengeId.class)
public class SubChallenge {

    @Id
    private String name;

    @Id
    @ManyToOne
    private Challenge challenge;

    private int steps;
}
