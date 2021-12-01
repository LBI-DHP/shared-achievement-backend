package at.lbg.dhp.sharedachievementbackend.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Challenge {

    @Id
    private String name;

    private int steps;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "challenge")
    private List<SubChallenge> subChallenges;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="challenge")
    private List<Team> teams;
}
