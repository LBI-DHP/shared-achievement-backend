package at.lbg.dhp.sharedachievementbackend.data.dto;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    @NotNull
    private String id;
    @NotNull
    private String name;
    @Nullable
    private String teamName;

}
