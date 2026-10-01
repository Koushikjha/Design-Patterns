package creational;

public class FactoryPattern {
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

    static class PaymentFactory{
        public PaymentStrategy get(String type){
            if(type.equalsIgnoreCase("UPI")){
                return new UpiPayment();
            }else if(type.equalsIgnoreCase("CARD")){
                return new CardPayment();
            }else{
                throw new IllegalArgumentException("Illegal Type");
            }
        }
    }


    public static void main(String[] args) {
        PaymentFactory paymentFactory=new PaymentFactory();
        PaymentStrategy paymentStrategy=paymentFactory.get("upi");
        paymentStrategy.pay(1000);
    }
}
