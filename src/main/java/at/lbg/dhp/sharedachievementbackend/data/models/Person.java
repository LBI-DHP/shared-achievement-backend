package at.lbg.dhp.sharedachievementbackend.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    public enum Gender {
        Male, Female, Diverse, NoAnswer;
    }


    @Id
    private String id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    private Team team;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<StepCount> stepCounts;

    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.NoAnswer;
    private int age;
    private int bodyWeight = 80;
    private int bodyHeight = 170;
    private int bodyMassIndex;
    private float intendedActivityLevel;

}
