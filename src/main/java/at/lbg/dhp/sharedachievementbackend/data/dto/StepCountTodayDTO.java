package at.lbg.dhp.sharedachievementbackend.data.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepCountTodayDTO {

    @NotNull
    private String personId;

    @NotNull
    private int steps;
}