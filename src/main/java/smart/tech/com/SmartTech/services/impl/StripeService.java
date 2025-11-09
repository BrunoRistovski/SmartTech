package smart.tech.com.SmartTech.services.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import smart.tech.com.SmartTech.model.DTO.ProductRequestDTO;
import smart.tech.com.SmartTech.model.DTO.StripeResponseDTO;

@Service
public class StripeService {

    //stripe-API
    @Value("${stripe.secretKey}")
    private String secretKey;

    //Stripe request -> productName,amount,currency,quantity
    //Stripe response -> status,message,sessionId,sessionUrl

    public StripeResponseDTO checkout(ProductRequestDTO productRequestDTO) {

        Stripe.apiKey = secretKey;

        SessionCreateParams.LineItem.PriceData.ProductData productData =SessionCreateParams.LineItem.PriceData.ProductData.builder()
                .setName(productRequestDTO.getName()).build();

        SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
                .setUnitAmount(productRequestDTO.getAmount())
                .setCurrency(productRequestDTO.getCurrency()==null?"USD":productRequestDTO.getCurrency())
                .setProductData(productData)
                .build();

        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                .setQuantity(productRequestDTO.getQuantity())
                .setPriceData(priceData)
                .build();

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("http://localhost:8080/api/products")
                        .setCancelUrl("http://localhost:8080/api/orders")
                        .addLineItem(lineItem)
                        .build();

        Session session = null;
        try {
            session = Session.create(params);
        } catch (StripeException e) {
            //log the error
        }

        return StripeResponseDTO.builder()
                .status("SUCCESS")
                .message("Payment session created ")
                .sessionId(session.getId())
                .sessionURL(session.getUrl())
                .build();

    }
}
