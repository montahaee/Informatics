package CRS.utility;

public class TestAdd<L,R> extends Operation1<L,R> {

     Operation1<Integer,Double> test = new Operation1<>();

    public int testAdd(Pair<Integer,Double> p) {
          var t =  test.operation(p , (int) (p.getX()+p.getY()));
//        super(ctor);
        return t.get();
    }
//    public int add(Pair<L,R> p ) {
//
//    }
//    public void test(Operation1<Double, Double> that) {
//        test =
//
//        test.operate();
//    }

}
