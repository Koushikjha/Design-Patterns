package creational;

public class BuilderPattern {
    static class User{
        private String name;
        private long phone;
        private int age;

        public User(String name, long phone, int age) {
        }
    }
    static class UserBuilder{
        private String name;
        private long phone;
        private int age;
        public UserBuilder name(String name){
            this.name=name;
            return this;
        }
        public UserBuilder age(int age){
            this.age=age;
            return this;
        }
        public UserBuilder phone(long phone){
            this.phone=phone;
            return this;
        }
        public User build(){
            return new User(name,phone,age);
        }
    }

    public static void main(String[] args) {
        User user=new UserBuilder().name("koushik").age(21).phone(1516655).build();
        System.out.println(user.toString()) ;
    }
}
