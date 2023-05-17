package springprinciple.core.order;

import springprinciple.core.discount.DiscountPolicy;
import springprinciple.core.discount.FixDiscountPolicy;
import springprinciple.core.discount.RateDiscountPolicy;
import springprinciple.core.member.Member;
import springprinciple.core.member.MemberRepository;
import springprinciple.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    // AppConfig에 생성자를 통해 주입 - DIP를 지킴
    private final MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
