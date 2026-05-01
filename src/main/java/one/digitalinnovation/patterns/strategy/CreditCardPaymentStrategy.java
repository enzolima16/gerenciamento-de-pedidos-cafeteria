package one.digitalinnovation.patterns.strategy;

import org.springframework.stereotype.Service;

@Service
public class CreditCardPaymentStrategy implements PaymentStrategy{
    @Override
    public String processPayment(){
        return "Pagamento via Cartão realizado.";
    }
}