package team.kyp.kypcoffee.domain.admin;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OnedayClass {
    private int classNum;
    private Date classDate;
    private int classCapacity;
    private String classPlace;
    private String classExplanation;
}
