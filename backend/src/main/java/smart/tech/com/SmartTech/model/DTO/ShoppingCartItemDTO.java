package smart.tech.com.SmartTech.model.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ShoppingCartItemDTO {

    @NotNull
    private Long shoppingCartId;

    @NotNull
    private Long productId;

    @NotNull
    private Integer quantity;
}
