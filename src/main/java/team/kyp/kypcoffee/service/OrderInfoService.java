package team.kyp.kypcoffee.service;

import team.kyp.kypcoffee.domain.Member;
import team.kyp.kypcoffee.domain.Product_info;

import java.util.ArrayList;
import java.util.List;

public interface OrderInfoService {

    Member memberInfoByMemberNum(int memberNum); // cartNum == 14 일 때, 이름, 주소 가져오기

    List<Product_info> productInfo(ArrayList<Integer> cartNum); // cartNum == 14 일 때, 상품명, 가격 가져오기
}
