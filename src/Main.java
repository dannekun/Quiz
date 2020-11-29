public class Main {

    public static void main(String[] args) {

        int a  = 1;
        int b = 2;
        int c = 3;
        for (int i = 1; i < 10; i++) {
            System.out.println("i: "+ i);
            System.out.println("a: "+i%a);
            System.out.println("b: "+i%b);
            System.out.println("c: "+ i%c);
            System.out.println();
        }

    }
}
