package structural;

public class ProxyPattern {
    interface Image{
        void display();
    }
    static class RealImage implements Image{
        String fileName;
        RealImage(String name){
            this.fileName=name;
            loadFromDisk();
        }
        private void loadFromDisk(){
            System.out.println("Loading Image..");
        }
        @Override
        public void display(){
            System.out.println("Display Image");
        }
    }
    static class ProxyImage implements Image{
        String fileName;
        RealImage realImage;
        ProxyImage(String name){
            this.fileName=name;
        }
        @Override
        public void display(){
            if(realImage==null){
                realImage=new RealImage(fileName);
            }
            realImage.display();
        }
    }

    public static void main(String[] args) {
        Image image=new ProxyImage("cat.txt");
        image.display();
    }
}
