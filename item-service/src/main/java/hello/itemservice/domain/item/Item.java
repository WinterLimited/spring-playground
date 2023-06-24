package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price; // price는 null이 될 수 없으므로 int가 아닌 Integer로 선언
    private Integer quantity; // quantity는 null이 될 수 없으므로 int가 아닌 Integer로 선언

    // 롬복의 @Data를 사용하면 아래와 같이 생성자를 만들지 않아도 됨
    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
