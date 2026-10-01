package creational;

public class SingletonPattern {
    static class Singleton{
        private static Singleton instance=new Singleton();
        private Singleton(){

        }
        public static Singleton getInstance(){
            return instance;
        }
    }

    public static void main(String[] args) {
        Singleton instance=Singleton.getInstance();
    }
}
