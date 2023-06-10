import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        String test0 = "mor,mon";
        String[] store;
        store = test0.trim().split(",");
        for (String s : store) {
            System.out.print(s + " ");
        }
        System.out.println();
        String test1 = "mor,mon,e";
        store = test1.trim().split(",");

        for (String s : store) {
            System.out.print(s + " ");
        }
        System.out.println();

        int[] istore  = {0,0};
        int[] test2 = {1,2,3};
//        System.arraycopy(test0.trim().split(","),0, store, 0, test0.trim().split(",").length-1);
        System.arraycopy(test2,0, istore, 0, test2.length-1);
        for (int j : istore) {
            System.out.print(j+" ");
        }
//        int t = 8*3/4*3;
        int t = 8*3/4;
        System.out.println("\nt:" + t);

    }
}
