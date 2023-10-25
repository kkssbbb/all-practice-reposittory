package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class OrderServiceTest {

    @PersistenceContext
    EntityManager em;
    @Autowired OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Test
    public void 상품주문() throws Exception {
        Member member = createMember();

        Book book = createBook("삼국지", 10000, 100);

        int orderCount = 2;

        //w
       Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        System.out.println("orderId = " + orderId);
       //t
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals("상품 주문시 상태는 ORDER",OrderStatus.ORDER,getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.",1, getOrder.getOrderItems().size());
        assertEquals("주문가격은 가격 * 수량",10000,getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야한다.",98,book.getStockQuantity());
    }



    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{
        //g
        Member member = createMember();
        Book book = createBook("삼국지", 10000, 100);

        int orderCount = 111;
        //w
        orderService.order(member.getId(), book.getId() ,orderCount);
        //t
        fail("재고 수량 부족 예외가 발생해야한다.");
    }

    @Test
    public void 주문취소() throws Exception{
        //g
        Member member = createMember();
        Book book = createBook("삼국지", 10000, 100);
        int count = 10;

        Long order = orderService.order(member.getId(), book.getId(), count);
        //w
        orderService.cancelOrder(order);

        //t
        Order getOrder = orderRepository.findOne(order);
        assertEquals("주문 취소시 상태는 캔슬이다.",OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문 취소된 상품은 그만큼 재고가 증가해야한다.", 100,book.getStockQuantity());
    }


    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("승빈");
        member.setAddress(new Address("서울", "역삼동", "13245"));
        em.persist(member);
        return member;
    }

}