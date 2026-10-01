package structural;

public class AdapterPattern {
    interface Payment{
        void pay(int amount);
    }
    static class UPIPayment implements Payment{
        @Override
        public void pay(int amount){
            System.out.println("Payment: "+amount);
        }
    }
    static class RazorPayApi{
        void processPayment(int amount){
            System.out.println("RazorPay: "+amount);
        }
    }
    static class RazorpayAdapter implements Payment{
        RazorPayApi razorPayApi;
        RazorpayAdapter(RazorPayApi razorPayApi){
            this.razorPayApi=new RazorPayApi();
        }
        @Override
        public void pay(int amount){
            razorPayApi.processPayment(amount);
        }
    }

    static class PaymentService{
        Payment payment;
        PaymentService(Payment payment){
            this.payment=payment;
        }
        public void pay(int amount){
            payment.pay(amount);
        }
    }

    public static void main(String[] args) {
        RazorPayApi razorPayApi=new RazorPayApi();
        Payment payment=new RazorpayAdapter(razorPayApi);
        PaymentService paymentService=new PaymentService(payment);
        paymentService.pay(1000);
    }
}
