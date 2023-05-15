package springprinciple.core.discount;

import springprinciple.core.member.Member;

public interface DiscountPolicy {

    /**
     * 할인금액
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
