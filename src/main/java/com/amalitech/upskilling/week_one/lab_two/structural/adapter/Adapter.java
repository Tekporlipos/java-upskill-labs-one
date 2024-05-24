package com.amalitech.upskilling.week_one.lab_two.structural;

class LegacyPaymentProcessor {
    public void processPayment(String paymentDetails) {
        System.out.println("Processing payment with legacy system: " + paymentDetails);
    }
}

interface PaymentGateway {
    void makePayment(String paymentDetails);
}

class PaymentAdapter implements PaymentGateway {
    private final LegacyPaymentProcessor legacyPaymentProcessor;

    public PaymentAdapter(LegacyPaymentProcessor legacyPaymentProcessor) {
        this.legacyPaymentProcessor = legacyPaymentProcessor;
    }

    @Override
    public void makePayment(String paymentDetails) {
        legacyPaymentProcessor.processPayment(paymentDetails);
    }
}

class AdapterPatternPaymentExample {
    public static void main(String[] args) {
        LegacyPaymentProcessor legacyProcessor = new LegacyPaymentProcessor();
        PaymentGateway paymentGateway = new PaymentAdapter(legacyProcessor);

        paymentGateway.makePayment("Payment details for modern gateway");
    }
}
