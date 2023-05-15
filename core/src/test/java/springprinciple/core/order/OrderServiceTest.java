package springprinciple.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import springprinciple.core.member.Grade;
import springprinciple.core.member.Member;
import springprinciple.core.member.MemberService;
import springprinciple.core.member.MemberServiceImpl;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "winterA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "winter", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
