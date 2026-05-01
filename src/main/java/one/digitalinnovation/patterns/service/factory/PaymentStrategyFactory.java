package one.digitalinnovation.patterns.service.factory;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.patterns.strategy.CashPaymentStrategy;
import one.digitalinnovation.patterns.strategy.CreditCardPaymentStrategy;
import one.digitalinnovation.patterns.strategy.PaymentStrategy;
import one.digitalinnovation.patterns.strategy.PaymentType;
import one.digitalinnovation.patterns.strategy.PixPaymentStrategy;

@Component
@RequiredArgsConstructor
public class PaymentStrategyFactory {
    private final PixPaymentStrategy pix;
    private final CreditCardPaymentStrategy creditCard;
    private final CashPaymentStrategy cash;

    public PaymentStrategy getStrategy(PaymentType type){
        return switch (type) {
            case PIX -> pix;
            case CREDIT_CARD -> creditCard;
            case CASH -> cash;
        };
    }
}
