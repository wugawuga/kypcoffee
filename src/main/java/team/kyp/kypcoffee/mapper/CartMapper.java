package team.kyp.kypcoffee.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.kyp.kypcoffee.domain.Cart;
import team.kyp.kypcoffee.domain.CartCommand;
import team.kyp.kypcoffee.domain.Product_info;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CartMapper {

    void cartAdd(CartCommand cartCommand);

    List<Cart> findAll(int memberNum);

    void delCart(int cartNum);

    List<Cart> cartsInfo(ArrayList<Integer> cartNum);
}
