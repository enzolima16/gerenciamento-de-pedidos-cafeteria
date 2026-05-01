package one.digitalinnovation.patterns.strategy;

import org.springframework.stereotype.Service;

@Service
public class CashPaymentStrategy implements PaymentStrategy{
    @Override
    public String processPayment(){
        return "Pagamento em Dinheiro realizado.";
    }
}
