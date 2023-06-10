package Cartography.framework;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Test {

    public static void main(String[] args) {
        List<Map.Entry<String, Integer>> test = new ArrayList<>();
        List<String> denotations = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
//            denotations.add(Character.toString(ThreadLocalRandom.current().nextInt(65,91)));
            int curr = ThreadLocalRandom.current().nextInt(65,91);
            test.add(new AbstractMap.SimpleEntry<>(Character.toString(curr), curr*2));
//           To avoid repetition of  generated denotations and respectively corresponding characteristics.
            Collections.shuffle(test);
        }
//        Map.Entry<String,Integer> u = new En
//        String t = String.valueOf(x);
//        while ()
//        String t = Character.toString(x);
        System.out.println(test);
        test.forEach((k->System.out.println(k.getValue())));
        test.forEach((k->System.out.println(k.getKey())));
//        StringBuilder tes= new StringBuilder("""
//                test
//                usw.
//                """);
//        System.out.println(tes);
        Orbit orbit1 = new Orbit(357,51.3,10);
        double d0 = orbit1.getDistance(52.2,5.3);
        Orbit orbit2 = new Orbit(42, 52.2,5.3);
        Orbit orbit3 = new Orbit(33, 50.7,4.8);
        double d2 = orbit2.getDistance(50.7,4.8);
        double radius2 = orbit2.radius();
        double radius1 =  orbit1.radius();
        double radius3 =  orbit3.radius();
        System.out.println("radius2: " + radius2);
        System.out.println("radius1: " + radius1);
        System.out.println("radius3: " + radius3);
        System.out.println("distance: " +d0);

        double rd1 = Math.sqrt((30.438864119050862 - 18.43880887779934 )*(30.438864119050862 - 18.43880887779934 ) +( 160.54316605183638- 152.73557977809224)*( 160.54316605183638- 152.73557977809224));
        System.out.println("true would be " + rd1);
//      Try to solve the problem using https://mathworld.wolfram.com/Circle-CircleIntersection.html
//        double x = (d0*d0 - radius1*radius1 + radius2*radius2)/(2*d0);
        System.out.println("finding x position as center of the circle on sphere");
//        double x = (d0*d0 - radius2*radius2 + radius1*radius1)/(2*d0);
        double x = Math.sqrt((d0*d0 - radius2*radius2 + radius1*radius1)/(2*d0));
        double x0 = (orbit1.getCharacteristic() * orbit2.getCharacteristic() / (4*Math.PI * 8.854187817*d0*d0));
        System.out.println("x using attraction force " + x0);
//        System.out.println("x: " + x);
//        System.out.println("x: " +Math.sqrt(x));
        System.out.println("x: " +x);
//        System.out.println("y: " + Math.sqrt(Math.abs(radius1*radius1 - x*x)));
        x = Math.sqrt((d2*d2 - radius3*radius3 + radius2*radius2)/(2*d2));
//        x = Math.sqrt(((d2 +d0)*(d2 +d0) - radius3*radius3 + radius2*radius2)/(2*(d2+d0)));
        System.out.println("x: " +x);

//        double d4 = orbit1.getDistance(50.7,4.8);
        System.out.println(d2);
        double rd3 = Math.sqrt((30.438864119050862 - 16.614607057837105 )*(30.438864119050862 - 16.614607057837105 ) +(152.73557977809224- 153.89137982415392)*(152.73557977809224 - 153.89137982415392));

//        double d3 = Math.sqrt((30.438864119050862 - 18.43880887779934 )*(30.438864119050862 - 18.43880887779934 ) +( 160.54316605183638- 153.89137982415392)*( 160.54316605183638- 153.89137982415392));
        System.out.println(rd3);

        System.out.println(rd3/rd1);
//        System.out.println(d4/d3);
        double rd4 = Math.sqrt((30.438864119050862 - 18.99936258255788)*(30.438864119050862 - 18.99936258255788 ) +(152.73557977809224- 150.36949557013983)*(152.73557977809224 - 150.36949557013983));
        System.out.println(rd4);
        System.out.println(rd4/rd1);
//        String filename = Paths.get("C:\\Users\\Montahaee\\Documents\\mypc\\proj\\wrk\\IHK\\exam\\2021-11-19\\SWE\\Informatics\\src\\Cartography\\framework\\test.txt").toAbsolutePath().toString();
        String filename = Paths.get("src","Cartography","framework/test.txt").toAbsolutePath().toString();
//        System.out.println(filename);
        File file = Paths.get(filename).toFile();
        List<Orbit> orbits = new ArrayList<>();
        try(Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] ss = sc.nextLine().split(" ");
                orbits.add(new Orbit(Integer.parseInt(ss[0]),Double.parseDouble(ss[2]),Double.parseDouble(ss[1])));
            }
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        }
        double sum = 0;
        for (int i = 0; i < orbits.size() - 1; i++) {
            sum += orbits.get(i).getDistance(orbits.get(i+1).getLatitude(),orbits.get(i+1).getLongitude());
        }
        System.out.println(Arrays.toString(orbits.toArray()));
        System.out.println("sum_distance: " + sum);
        System.out.println();
    }

}
