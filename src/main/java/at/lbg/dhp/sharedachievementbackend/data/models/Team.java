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
public class Team {

    @Id
    private String name;

    @ManyToOne
    private Challenge challenge;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    private List<Person> persons;
}
