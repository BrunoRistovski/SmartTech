package smart.tech.com.SmartTech.model.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter

public class OrderItemDTO {

    @NotNull
    private Long orderId;
    @NotNull
    private Long productId;
    @NotNull
    private Integer quantity;

     public OrderItemDTO(Long orderId, Long productId, Integer quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
