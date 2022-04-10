package team.kyp.kypcoffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import team.kyp.kypcoffee.domain.Member;
import team.kyp.kypcoffee.domain.Product_info;
import team.kyp.kypcoffee.mapper.OrderMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderInfoServiceImpl implements OrderInfoService {

    private final OrderMapper mapper;

    @Override
    public Member memberInfoByMemberNum(int memberNum) {

        return mapper.memberInfoByMemberNum(memberNum);
    }

    @Override
    public List<Product_info> productInfo(ArrayList<Integer> cartNum) {

        return mapper.productInfo(cartNum);
    }
}
