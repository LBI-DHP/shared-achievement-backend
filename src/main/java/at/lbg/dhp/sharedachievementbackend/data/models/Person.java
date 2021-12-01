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

    @Id
    private String id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    private Team team;

    @OneToMany(mappedBy="person", cascade=CascadeType.ALL)
    private List<StepCount> stepCounts;
}
