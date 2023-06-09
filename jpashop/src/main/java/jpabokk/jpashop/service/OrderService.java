package jpabokk.jpashop.service;

import jpabokk.jpashop.domain.Delivery;
import jpabokk.jpashop.domain.Item.Item;
import jpabokk.jpashop.domain.Member;
import jpabokk.jpashop.domain.Order;
import jpabokk.jpashop.domain.OrderItem;
import jpabokk.jpashop.repository.ItemRepository;
import jpabokk.jpashop.repository.MemberRepository;
import jpabokk.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAdress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();
    }


    // TODO: 검색
//    public List<Order> findOrders(OrderSearch orderSearch) {
//        return orderRepository.findAllByString(orderSearch);
//    }

}
