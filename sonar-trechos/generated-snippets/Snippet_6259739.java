public static class DivisionWithZero extends Division {

		@Override
		public double doubleOperate(double a, double b) {
			return b == 0 ? 0 : a / b;
		}

		@Override
		public void complexOperate(double[] out, double ra, double ia, double rb, double ib) {
			double q;
			double den;
			if (ib == 0) {
				if (rb == 0) {
					out[0] = 0;
					out[1] = 0;
				} else {
					out[0] = ra / rb;
					out[1] = ia / rb;
				}
			} else if (rb == 0) {
				out[0] = ia / ib;
				out[1] = -ra / ib;
			} else if (Math.abs(rb) < Math.abs(ib)) {
				q = rb / ib;
				den = rb * q + ib;
				out[0] = (ra * q + ia) / den;
				out[1] = (ia * q - rb) / den;
			} else {
				q = ib / rb;
				den = ib * q + rb;
				out[0] = (ia * q + ra) / den;
				out[1] = (ia - ra * q) / den;
			}
		}
	}
