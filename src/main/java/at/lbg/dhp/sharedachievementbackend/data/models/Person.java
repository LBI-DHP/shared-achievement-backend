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


    public enum PhysicalActivity {
        PhysicallyActive,
        ActiveButSedentary,
        SlightlyActive,
        ExtremelySedentary
    }

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
    private int age; // age in years
    private int bodyWeight = 80; // weight in kilogtamm
    private int bodyHeight = 170; // height in centimeters
    private float bodyMassIndex;
    
    @Enumerated(EnumType.STRING)
    private PhysicalActivity physicalActivity;

    public void calculateBMI() {
        float height = (float)this.bodyHeight / 100.0f;
        this.bodyMassIndex = bodyWeight / (height*height);
    }

}
