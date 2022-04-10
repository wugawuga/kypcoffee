package team.kyp.kypcoffee.service;

import team.kyp.kypcoffee.domain.Cart;
import team.kyp.kypcoffee.domain.CartCommand;
import team.kyp.kypcoffee.domain.Product_info;

import java.util.ArrayList;
import java.util.List;

public interface CartService {

    void createCart(CartCommand cartCommand);

    List<Cart> findAll(int memberNum);

    void delCart(int cartNum);

    List<Cart> cartsInfo(ArrayList<Integer> cartNum);
}
