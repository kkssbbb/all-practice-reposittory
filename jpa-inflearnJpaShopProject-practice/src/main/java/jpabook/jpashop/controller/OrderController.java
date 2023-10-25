package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    //주문폼 이동
    @GetMapping("/order")
    public String createForm(Model model) {

        List<Member> members = memberService.findMember();
        List<Item> items = itemService.finItem();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    //주문 데이터 받기
    @PostMapping(value = "/order")
    public String createOrder(@RequestParam("memberId") Long memberId,
                              @RequestParam("itemId") Long itemId,
                              @RequestParam("count") int count
                              ) {
        orderService.order(memberId, itemId, count);
        return "redirect:/order";
    }

    //주문내역//

    @GetMapping("/orde rs")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders" , orders);

        return "order/orderList";
    }

    //주문 취소
    @PostMapping("/order/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect/orders";
    }
}
