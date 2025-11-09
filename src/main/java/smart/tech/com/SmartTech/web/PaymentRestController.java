package smart.tech.com.SmartTech.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smart.tech.com.SmartTech.model.DTO.ProductRequestDTO;
import smart.tech.com.SmartTech.model.DTO.StripeResponseDTO;
import smart.tech.com.SmartTech.services.impl.StripeService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/payment")
public class PaymentRestController {

    private final StripeService stripeService;

    public PaymentRestController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<StripeResponseDTO> checkoutProducts(@RequestBody ProductRequestDTO productRequestDTO) {
        StripeResponseDTO stripeResponseDTO = stripeService.checkout(productRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stripeResponseDTO);
    }
}
