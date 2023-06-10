package Cartography;


//import Cartography.framework.BaseDescription1;

import Cartography.framework.Description;
import Cartography.framework.InputDescription;
import Cartography.framework.Orbit;
import Cartography.framework.OutputDescription;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Montahaee
 * @version 1.0
 * @created 23-Aug-2022 4:14:47 PM
 */
public class Demo {

	/**
	 * 
	 * @param args is for command line arguments.
	 */
	public static void main(String[] args){
//		List<Map.Entry<String, Integer>> test = new ArrayList<>();
//		Description<String,Integer> description = new BaseDescription1("etwas", test);
//		var t = description.content();
//		Description
//		Description description =new BaseDescription("test");
//		description = new InputDescription(description);
		Map<Description, Description> inout = new HashMap<>();
//		Map.Entry<Description,Description>
//		List<Map.Entry<String, Integer>> test = new ArrayList<>();
		List<Map.Entry<String, Orbit>> content = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
//            denotations.add(Character.toString(ThreadLocalRandom.current().nextInt(65,91)));
			int intCurr = ThreadLocalRandom.current().nextInt(65,91);
			double doubleCurr = ThreadLocalRandom.current().nextDouble(65,91);
//			test.add(new AbstractMap.SimpleEntry<>(Character.toString(intCurr), 2*intCurr));
			content.add(new AbstractMap.SimpleEntry<>(Character.toString(intCurr),
					new Orbit(2* intCurr, 5*doubleCurr, doubleCurr)));
//           To avoid repetition of  generated denotations and respectively corresponding characteristics.
			Collections.shuffle(content);
		}

		Description input = new InputDescription();
		input.setContent(content);
		input.setSubject("Idiots:-)");
		System.out.println(input.toString());
		Description output = new OutputDescription();
//		output = input.clone();
		output.setContent(content);
//		output = input.clone();
//		output.setSubject(input.getSubject());
		System.out.println(output.toString());
		BigInteger myBI = new BigInteger("100000000000000");
		double d = 0.00000000000000000011;
		BigDecimal d_myBI= new BigDecimal(myBI);
		BigDecimal rs = d_myBI.multiply(BigDecimal.valueOf(d));
		System.out.println(d);
		System.out.println(rs);


	}
}//end Demo