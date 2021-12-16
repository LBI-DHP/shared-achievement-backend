package at.lbg.dhp.sharedachievementbackend.data.dto;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamMembersDTO {

    private String name;
    private Challenge challenge;
    private List<Person> persons;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Challenge {
        private String name;
        private int steps;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Person {
        private String id;
        private String name;
        private int steps;
        private double relativeNoOfSteps;
    }
}
