package springprinciple.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import springprinciple.core.discount.FixDiscountPolicy;
import springprinciple.core.member.Grade;
import springprinciple.core.member.Member;
import springprinciple.core.member.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}