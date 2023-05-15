package springprinciple.core;

import springprinciple.core.discount.DiscountPolicy;
import springprinciple.core.discount.FixDiscountPolicy;
import springprinciple.core.discount.RateDiscountPolicy;
import springprinciple.core.member.MemberService;
import springprinciple.core.member.MemberServiceImpl;
import springprinciple.core.member.MemoryMemberRepository;
import springprinciple.core.order.OrderService;
import springprinciple.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // memberRepository Config
    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // discountPolicy Config
    public DiscountPolicy discountPolicy() {
        // DI를 통해 구현
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
