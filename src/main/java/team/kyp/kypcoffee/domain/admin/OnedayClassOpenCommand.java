package team.kyp.kypcoffee.domain.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnedayClassOpenCommand {
    private String classDate;
    private int classCapacity;
    private String classPlace;
    private String classExplanation;
}
