package CRS.utility;

public class Add2<L,R> extends Test2<L,R>{
    private Operation2<Double,Double> op;
    public Add2(Operation2<L, R> op, Operation2<Double, Double> op1) {
        super(op);
        this.op = op1;
    }

    public double add(Pair<Double,Double> p) {
        var t = op.operation(p, p.getX() + p.getY());
        return t.get();
    }
//    Operation<L,R> operation = new Additions<>();
//    var t = operation.operate(op,0.0)
    public  void test (Pair<Double,Double> p) {
        Context<L,R> context = new Context<>();
//        context.calculate(p)
    }
}
