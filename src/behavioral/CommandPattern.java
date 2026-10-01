package behavioral;

public class CommandPattern {
    interface Command{
        void execute();
    }
    static class Tv{
        public void turnOn(){
            System.out.println("Tv ON");
        }
        public void turnOff(){
            System.out.println("Tv OFF");
        }
    }
    static class TurnOnCommand implements Command{
        Tv tv;
        TurnOnCommand(Tv tv){
            this.tv=tv;
        }
        @Override
        public void execute(){
            tv.turnOn();
        }
    }
    static class TurnOffCommand implements Command{
        Tv tv;
        TurnOffCommand(Tv tv){
            this.tv=tv;
        }
        @Override
        public void execute(){
            tv.turnOff();
        }
    }
    static class Remote{
        Command command;
        public void setCommand(Command command){
            this.command=command;
        }
        public void pressButton(){
            command.execute();
        }
    }

    public static void main(String[] args) {
        Tv tv=new Tv();
        Remote remote=new Remote();

        Command on=new TurnOnCommand(tv);
        remote.setCommand(on);
        remote.pressButton();

        Command off=new TurnOffCommand(tv);
        remote.setCommand(off);
        remote.pressButton();
    }
}
