package team.kyp.kypcoffee.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.kyp.kypcoffee.domain.Cart;
import team.kyp.kypcoffee.domain.CartCommand;
import team.kyp.kypcoffee.domain.Product_info;
import team.kyp.kypcoffee.mapper.CartMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper mapper;

    @Override
    public void createCart(CartCommand cartCommand) {

        mapper.cartAdd(cartCommand);
    }

    @Override
    public List<Cart> findAll(int memberNum) {

        return mapper.findAll(memberNum);
    }

    @Override
    public void delCart(int cartNum) {

        mapper.delCart(cartNum);
    }

    @Override
    public List<Cart> cartsInfo(ArrayList<Integer> cartNum) {

        return mapper.cartsInfo(cartNum);
    }
}
