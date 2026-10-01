package behavioral;

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {
    interface Observer {
        void update(String message);
    }
    static class Subscriber implements Observer {
        private String name;
        Subscriber(String name){
            this.name=name;
        }
        @Override
        public void update(String message){
            System.out.println(name+" received "+message);
        }
    }
    interface Subject{
        void subscribe(Observer observer);
        void unsubscribe(Observer observer);
        void notifyClients();
    }
    static class YoutubeChannel implements Subject{
        private List<Observer> observers =new ArrayList<>();
        private String title;


        @Override
        public void subscribe(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void unsubscribe(Observer observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyClients() {
            for(Observer observer : observers){
                observer.update("new video: "+title);
            }
        }

        public void upload(String videoTitle){
            this.title=videoTitle;
            notifyClients();
        }
    }

    public static void main(String[] args) {
        Observer a=new Subscriber("Koushik");
        Observer b=new Subscriber("Raj");
        YoutubeChannel channel =new YoutubeChannel();
        channel.subscribe(a);
        channel.subscribe(b);
        channel.unsubscribe(b);
        channel.upload("My Journey");
    }
}
