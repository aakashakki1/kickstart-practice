public class Main {

    public static void main(String[] args) {
	// write your code here
        float f = (float) 7.1234;
        String s = "3.1456";
        float p = Float.parseFloat(s);
        String k = String.format("%.2f",p);
        System.out.println(k);
    }
}
