package springprinciple.core.order;

import springprinciple.core.discount.DiscountPolicy;
import springprinciple.core.discount.FixDiscountPolicy;
import springprinciple.core.member.Member;
import springprinciple.core.member.MemberRepository;
import springprinciple.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
