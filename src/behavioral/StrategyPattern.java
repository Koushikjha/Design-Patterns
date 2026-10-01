package behavioral;

public class StrategyPattern {
    interface PaymentStrategy{
        void pay(int val);
    }
    static class UpiPayment implements PaymentStrategy{

        @Override
        public void pay(int val) {
            System.out.println("Upi Payment Done");
        }
    }

    static class CardPayment implements PaymentStrategy{

        @Override
        public void pay(int val){
            System.out.println("Card Payment Done ");
        }
    }

    static class PaymentService{
        private PaymentStrategy paymentStrategy;
        PaymentService(PaymentStrategy paymentStrategy){
            this.paymentStrategy=paymentStrategy;
        }

        public void pay(int val){
            paymentStrategy.pay(val);
        }
    }

    public static void main(String[] args) {
        PaymentService paymentService=new PaymentService(new UpiPayment());
        paymentService.pay(1000);
    }
}
