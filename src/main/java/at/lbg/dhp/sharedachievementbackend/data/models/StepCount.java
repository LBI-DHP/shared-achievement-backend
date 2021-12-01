package at.lbg.dhp.sharedachievementbackend.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(StepCountId.class)
public class StepCount {

    @Id
    @Column(columnDefinition = "DATE")
    private LocalDate day;

    @Id
    @ManyToOne
    private Person person;

    private int steps;
}
