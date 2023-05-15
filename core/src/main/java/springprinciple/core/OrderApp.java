package springprinciple.core;

import springprinciple.core.member.Grade;
import springprinciple.core.member.Member;
import springprinciple.core.member.MemberService;
import springprinciple.core.member.MemberServiceImpl;
import springprinciple.core.order.Order;
import springprinciple.core.order.OrderService;
import springprinciple.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "winterA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "winter", 20000);
        System.out.println("order price: " + order.calculatePrice());
    }
}
