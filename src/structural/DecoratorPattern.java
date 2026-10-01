package structural;

public class DecoratorPattern {
    interface Coffee{
        int cost();
        String description();
    }
    static class BasicCoffee implements Coffee{

        @Override
        public int cost() {
            return 100;
        }

        @Override
        public String description() {
            return "Basic Coffee";
        }
    }
    static abstract class CoffeeDecorator implements Coffee{
        protected Coffee coffee;
        public CoffeeDecorator(Coffee coffee){
            this.coffee=coffee;
        }
    }
    static class MilkDecorator extends CoffeeDecorator{
        public MilkDecorator(Coffee coffee){
            super(coffee);
        }
        @Override
        public int cost(){
            return coffee.cost()+20;
        }
        @Override
        public String description(){
            return coffee.description()+" Milk";
        }
    }
    static class SugarDecorator extends CoffeeDecorator{
        public SugarDecorator(Coffee coffee){
            super(coffee);
        }
        @Override
        public int cost(){
            return coffee.cost()+10;

        }
        @Override
        public String description(){
            return coffee.description()+" Sugar";
        }
    }

    public static void main(String[] args) {
        Coffee coffee=new BasicCoffee();
        coffee=new MilkDecorator(coffee);
        coffee=new SugarDecorator(coffee);
    }
}
