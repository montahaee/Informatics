package CRS.Multiplications;

import CRS.frameWork.CRS;

public class CRSProduct implements Multiplications<CRS, CRS>{

    /**
     * @param left  operand
     * @param right operand
     * @return result of production of the two operands.
     */
    @Override
    public CRS multiply(CRS left, CRS right) {

        right = right.T();
        if (left.getMat_size() != right.getMat_size()) {
            throw new ArithmeticException("The both matrices are restricted to be squared" +
                    " and therefore their size must be the same.");
        }
        int lp, rp;
        CRS result = new CRS(left);
        result.reset();
        int[] ptr = new int[left.getRow_ptr().length];

        for (lp = 0; lp < left.getNonzero_values().length; ) {
//				get current row of result matrix
            int cr = left.getRow_ptr()[lp];

            for (rp = 0; rp < right.getNonzero_values().length; ) {
//					current column of result matrix
                int cc = right.getRow_ptr()[rp];

//					temporary pointers
                int l_temp = lp;
                int r_temp = rp;
                int sum = 0;

//					search elements with the same row and column value and sum them.
                while (l_temp < left.getNonzero_values().length && left.getRow_ptr()[l_temp] == cr &&
                        r_temp < right.getNonzero_values().length && right.getRow_ptr()[r_temp] == cc) {
                    if (left.getCol_indices()[l_temp] < right.getCol_indices()[r_temp]) {
                        l_temp++;
                    } else if (left.getCol_indices()[l_temp] > right.getCol_indices()[r_temp]) {
                        r_temp++;
                    } else {
                        sum += left.getNonzero_values()[l_temp++] * right.getNonzero_values()[r_temp];
                    }
                }
                if (sum != 0) {
                    result.insert(cr, cc, sum);
                }
                while (rp < right.getMat_size() && right.getRow_ptr()[rp] == cc) {
                    rp++;
                }
            }
            while (lp < left.getMat_size() && left.getRow_ptr()[lp] == cr) {
                lp++;
            }
        }
        return result;
    }
}
