package RNG.rng;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class Test {

    /**
     *
     * @param args contains the supplied command-line-arguments
     */
    public static void main(String[] args){

        BigInteger myBI = new BigInteger("100000000000000");
        double d = 0.00000000000000000011;
        double ed = 1.0;
        BigInteger nd = new BigInteger("1000000000000000000000");
        BigDecimal d_myBI= new BigDecimal(myBI);
        System.out.println("1. " +myBI.doubleValue());
        System.out.println("2. " +nd.longValue());
//        System.out.println("3. " + (Long.) 1000000000000000000000);
        System.out.println(myBI);
        BigDecimal dbi_myBI = d_myBI.divide(new BigDecimal(nd), MathContext.UNLIMITED);
        System.out.println("bi_myBI: " + dbi_myBI);
        dbi_myBI = dbi_myBI.add(BigDecimal.valueOf(12));
        BigDecimal rs = d_myBI.multiply(BigDecimal.valueOf(d));
        BigDecimal ars = d_myBI.add(BigDecimal.valueOf(ed));
        System  .out.println("rs: " + rs);
        System  .out.println("ars: " + ars);
        double drs = rs.doubleValue();

        System.out.println("bi_myBI: " + dbi_myBI.doubleValue());
        BigInteger ndd = new BigInteger("12");

        System.out.println("bi_myBI: " + d_myBI.divide(new BigDecimal(ndd), MathContext.DECIMAL64));
        System.out.println(d);
        System.out.println(rs);
        System.out.println(drs);
        var brs = d_myBI.divide(BigDecimal.valueOf(12),MathContext.DECIMAL128);
        System.out.println(brs);
        BigDecimal bd = new BigDecimal("21111111111111");
        double ibd = bd.doubleValue();
        System.out.println(bd);
        System.out.println(ibd);
        System.out.println(BigDecimal.valueOf(Math.pow(10.0,10)));
        System.out.println(BigDecimal.valueOf(Math.pow(10.0,10)));
        System.out.println((BigInteger.valueOf((long) Math.pow(10.0,10))) );
        System.out.println((long) Math.pow(10.0,12)) ;
        System.out.println(BigDecimal.valueOf((long) Math.pow(10,12)).compareTo(new BigDecimal(new BigInteger("427419669081")))) ;
        System.out.println(BigDecimal.valueOf(Math.pow(10.0,12))) ;
        System.out.println(BigDecimal.valueOf(Math.pow(10.0,12) -11).toBigInteger()) ;
        System.out.println(Math.pow(10.0,12) - 11);
    }
}
