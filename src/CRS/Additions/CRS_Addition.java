package CRS.Additions;

import CRS.frameWork.CRS;

import java.util.ArrayList;
import java.util.List;

public class CRS_Addition implements Additions<CRS>{

///    /**
//     * @return the mathematical identity property of addition
//     * wich around scalar '0' and by matrix zero matrix
//     */
///    @Override
///    public CRS identity() {
//        double[][] matrix = new double[0][0];
//        return new CRS(matrix);
//    }

    /**
     * @param left  is left operand
     * @param right is right operand
     * @return sum of the two operands as
     */
    @Override
    public CRS add(CRS left, CRS right) {

        if (left.getMat_size() != right.getMat_size()) {
            throw new IllegalArgumentException("Addition two matrices with different size " +
                    "not algebraically well defined!");
        }
//		I sum = identity();
        int n = left.getMat_size();
        int[] ic = new int[n + 1];
        List<Integer> ojc = new ArrayList<>();
        List<Double> owc = new ArrayList<>();
        int[] il = left.getRow_ptr();
        int[] jl = left.getCol_indices();
        double[] wl = left.getNonzero_values();
        int[] ir = right.getRow_ptr();
        int[] jr = right.getCol_indices();
        double[] wr = right.getNonzero_values();
        ic[0] = 0;
        for (int m = 0; m <n; m++){
//			int min_idx = Math.min(il[m], ir[m]);
            int c;

//			The same row start index for left and right matrix
            if (il[m] <= ir[m]) {
//				for (int k = 0; k < n*(m + 1) - il[m]; k++){
                for (c = 0; c <= n - il[m] && wl[il[m] +c] != 0; c++){
//				for (int c = 0; c < n; c++){
//					ic[m] = c + il[m];
//					ic[m] = c + il[m];
//					if(jl[ic[m]] < jr[ic[m]]) {
                    if(jl[il[m]] < jr[il[m]]) {

//						ic[m] = c + il[m];
                        ojc.add(il[m] + c, jl[il[m]+c]);
                        owc.add(il[m] + c, wl[il[m] + c]);
//						k++;
                    } else if (jl[il[m]] == jr[il[m]]) { // the same column index
//					} else if (jl[ic[m]] == jr[ic[m]]) { // the same column index
//						ic[m] = c + il[m];
                        ojc.add(il[m]+ c, jl[il[m] + c]);
                        owc.add(il[m] + c, wl[il[m] + c] + wr[il[m] + c]);
//						k++;
                    } else {
//						ic[m] = c + ir[m];
                        ojc.add(il[m] + c, jr[il[m] + c]);
                        owc.add(il[m] +c, wr[il[m] + c]);
                    }
                }
                ic[++m] += c;
//			} else if (il[m] > ir[m]) {
            } else {
                for (c = 0; c <= n - ir[m] && wl[ir[m] +c] != 0; c++){
//					ic[m] = ir[m];
//					ojc.add();
                    if(jl[ir[m]] < jr[ir[m]]) {

//						ic[m] = c + il[m];
                        ojc.add(ir[m] + c, jl[ir[m]+c]);
                        owc.add(ir[m] + c, wl[ir[m] + c]);
//						k++;
                    } else if (jl[ir[m]] == jr[ir[m]]) { // the same column index
//					} else if (jl[ic[m]] == jr[ic[m]]) { // the same column index
//						ic[m] = c + il[m];
                        ojc.add(ir[m]+ c, jl[ir[m] + c]);
                        owc.add(ir[m] + c, wl[ir[m] + c] + wr[ir[m + c]]);
//						k++;
                    } else {
//						ic[m] = c + ir[m];
                        ojc.add(ir[m] + c, jr[ir[m] + c]);
                        owc.add(ir[m], wr[ir[m]]);
                    }
                }
                ic[++m] += ++c;
            }
//			for (int k = 0; k < n - il[m]; k++){
//				if (il[k + il[m]] == ir[k + i]) {
//					ic[k]  = il[k];
//					ojc.add(ic[k], jl[k]);
//				} else
//			}
        }
        //		int[] jc = new int[ojc.size()];
        int[] jc = ojc.stream().mapToInt(e->e).toArray();
//		Integer[] jc = new Integer[ojc.size()];
        double[] wc = new double[owc.size()];
//		jc = ojc.toArray(jc);
        wc = owc.stream().mapToDouble(e->e).toArray();
//		System.arraycopy(ojc.toArray(),0,jc,0,ojc.size());
//		System.arraycopy(owc,0,wc,0,ojc.size());

//		CRS result = new CRS()
//		if (p.)
//		CRS crs = new CRS(wc, jc, ic, n);
//
//		Additions<CRS,CRS> crs = new Additions<>(CRS::new)
//		L < CRS::new > crs ;
////		return new
//		return new Additions<>(CRS::new(wc, jc, ic, n));
//		return CRS.class.cast(crs);
//		Additions<CRS> crt = new Additions<>(ctor);
//		CRS crs = new Additions<CRS>(CRS::new)
//		return (new Additions<CRS::new> ;
//		L x = new L
//		L crs = new CRS(wc, jc, ic, n);
        return new CRS (wc, jc, ic, n);
    }
}
