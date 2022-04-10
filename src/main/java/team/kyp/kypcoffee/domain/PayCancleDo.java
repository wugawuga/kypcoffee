package team.kyp.kypcoffee.domain;

import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PayCancleDo {

    private String code;
    private String message;
    private JSONObject response=new JSONObject();
}
