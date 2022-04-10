package team.kyp.kypcoffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.kyp.kypcoffee.domain.AuthInfo;
import team.kyp.kypcoffee.domain.Cart;
import team.kyp.kypcoffee.domain.CartCommand;
import team.kyp.kypcoffee.domain.OrderCommand;
import team.kyp.kypcoffee.service.CartService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/cartAdd")
    public String cartAdd(CartCommand cartCommand) {

        cartService.createCart(cartCommand);

        return "redirect:/product";
    }

    @GetMapping("/cartList")
    public String cartList(HttpSession session, OrderCommand orderCommand, Model model) {

        AuthInfo ai = (AuthInfo) session.getAttribute("authInfo");
        List<Cart> cartList = cartService.findAll(ai.getNo());

        model.addAttribute("list", cartList);

        return "product/cartList";
    }

    @DeleteMapping ("/cartList/del/{cartNum}")
    @ResponseBody
    public void delCart(@PathVariable("cartNum") int cartNum) {

        cartService.delCart(cartNum);
    }
}
