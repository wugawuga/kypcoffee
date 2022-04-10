package team.kyp.kypcoffee.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OnedayClassNum {
    private int classNum;
    private Date classDate;
    private int classCapacity;
    private int classApplicantsNum;
    private String classExplanation;
    private String classPlace;
}
