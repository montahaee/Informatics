package CRS;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;

public class Test<L,R> {

//    public double add(Pair<L, R> p) {
//        if (p.getX() instanceof  Double && p.getY() instanceof Double) {
//            var t = operate(p,(double)p.getX()+ (double)p.getY());
//            return (double)t.get();
//        }

    public static void main(String[] args) throws IOException {
        double d = 65.43;
        Double D = 65.43;
        byte[] output = new byte[8];
        long lng = Double.doubleToLongBits(d);
        for(int i = 0; i < 8; i++) output[i] = (byte)((lng >> ((7 - i) * 8)) & 0xff);
        System.out.println(output.length);
        System.out.println(D.byteValue());
        System.out.println(Arrays.toString(output));
        double[] test = {1.0,1.1,1.2,1.3,1.4,1.5};
        double[] test1 = {10,11,12,13,14,15};
        long[] l = new long[test.length];
        for (int i = 0; i < test.length; i++) {
            l [i] = Double.doubleToLongBits(test[i]);
        }
        System.out.println(Arrays.toString(l));
        System.out.println(l.length);
        ByteBuffer buffer = ByteBuffer.allocate(Double.BYTES);
//        buffer.putDouble(d);
//        buffer.rewind();
//        byte[] bytes = buffer.array();
//        System.out.println(bytes.length);

        byte[]tb = buffer.array();
        for (double t : test) {
            buffer.putDouble(t);
            tb = buffer.array();
            buffer.rewind();

        }
        System.out.println(tb.length);
//      B

        List<byte[]> list = new ArrayList<>();
        for (double t : test) {
            buffer.putDouble(t);
            list.add(buffer.array());

            buffer.rewind();

        }
        int size = 0;
        byte[] sum = buffer.putDouble(test[0]).array();
        for (byte[] bytes : list) {
            size += bytes.length;
        }
        System.out.println(size);
        ByteBuffer byteBuffer = ByteBuffer.allocate(test.length * Double.BYTES);
        Arrays.stream(test).forEach(byteBuffer::putDouble);
        byte[] ts = byteBuffer.array();
        System.out.println(ts.length);
        DoubleStream stream = Arrays.stream(test);
//        ByteBuffer r ;
        InputStream is = new ByteArrayInputStream(ts);
        var r = is.read(ts);
        System.out.println(r);

//        InputStream ist = new ByteArrayInputStream(new byte[]{1.0,1.1,1.2,1.3,1.4,1.5})
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(test1.length * Double.BYTES);
        Arrays.stream(test1).forEach(byteBuffer1::putDouble);
        byte[] ts1 = byteBuffer.array();
        System.out.println(ts1.length);
//    Hier was first try to understand why in the example 2 A and B have size 172 and not 176.
//    But I think is type error similar to size of skalar which can have a size between 4 and 8 (Integer-Double).
//        TODO think about usefulness of Decorator for operation w.r.t. Strategy
//        Operation<Double, Double> op = new
//        Operation<Double,Double> operation = new OperationDecorator<>(op);
//        operation.operate(new Pair<>(4.0,4.0),)
//        OperationDecorator<CRS, CRS> op = new Additions<>();
//        op.operate().get()
//        Additions<CRS,CRS> opa = new Additions<>();
//        opa.
//        Operation operation = new Add<>();

    }





}
