package at.lbg.dhp.sharedachievementbackend.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepCountDTO {
    @NotNull
    private String personId;

    @NotNull
    private LocalDate day;

    @NotNull
    private int steps;
}
