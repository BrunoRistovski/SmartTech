package smart.tech.com.SmartTech.model.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import smart.tech.com.SmartTech.model.enumerations.Category;

@Getter
@Setter
public class ProductDTO {

    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String imageUrl;
    @NotNull
    private Category category;
    @NotNull
    private Double price;
    @NotNull
    private Integer stockQuantity;


}
