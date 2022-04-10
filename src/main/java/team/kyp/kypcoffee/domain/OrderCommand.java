package team.kyp.kypcoffee.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class OrderCommand {

    private ArrayList<Integer> cartNum;

}