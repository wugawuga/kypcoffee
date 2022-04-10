package team.kyp.kypcoffee.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.kyp.kypcoffee.domain.Member;
import team.kyp.kypcoffee.domain.Product_info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface OrderMapper {

    Member memberInfoByMemberNum(int memberNum);

    List<Product_info> productInfo(ArrayList<Integer> cartNum);
}
