package one.digitalinnovation.patterns.strategy;

import org.springframework.stereotype.Service;

@Service
public class PixPaymentStrategy implements PaymentStrategy{
    @Override
    public String processPayment(){
        return "Pagamento via PIX realizado.";
    }
}