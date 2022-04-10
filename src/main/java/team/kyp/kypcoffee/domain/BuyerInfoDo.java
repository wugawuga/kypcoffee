package team.kyp.kypcoffee.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.JSONObject;

@NoArgsConstructor
@Data
public class BuyerInfoDo {

    private String code;
    private String message;
    private JSONObject response = new JSONObject();
}
