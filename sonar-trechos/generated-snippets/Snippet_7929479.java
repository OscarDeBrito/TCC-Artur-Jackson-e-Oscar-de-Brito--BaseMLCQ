class XComparator implements Comparator {
		public int compare(Object arg0, Object arg1) {
			InternalNode n1 = (InternalNode) arg0;
			InternalNode n2 = (InternalNode) arg1;
			if (n1.getInternalX() > n2.getInternalX())
				return +1;
			else if (n1.getInternalX() < n2.getInternalX())
				return -1;
			else {
				return n1.toString().compareTo(n2.toString());
			}

		}
	}
