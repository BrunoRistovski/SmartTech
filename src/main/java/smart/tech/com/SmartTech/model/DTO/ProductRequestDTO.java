package smart.tech.com.SmartTech.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    private Long amount;
    private Long quantity;
    private String currency;
    private String name;
}
