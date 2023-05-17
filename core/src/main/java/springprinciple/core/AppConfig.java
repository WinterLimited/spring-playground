package springprinciple.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springprinciple.core.discount.DiscountPolicy;
import springprinciple.core.discount.FixDiscountPolicy;
import springprinciple.core.discount.RateDiscountPolicy;
import springprinciple.core.member.MemberService;
import springprinciple.core.member.MemberServiceImpl;
import springprinciple.core.member.MemoryMemberRepository;
import springprinciple.core.order.OrderService;
import springprinciple.core.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }


    // memberRepository Config
    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }


    // discountPolicy Config
    @Bean
    public DiscountPolicy discountPolicy() {
        // DI를 통해 구현
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
