public class Snippet__7095948 {

    @Deprecated
        protected void handleComputeFields(int julianDay) {
            int year, month, dayOfMonth, dayOfYear;

            long daysSinceEpoch = julianDay - PERSIAN_EPOCH;
            year = 1 + (int) floorDivide(33 * daysSinceEpoch + 3, 12053);

            long farvardin1 = 365L * (year - 1L) + floorDivide(8L * year + 21, 33L);
            dayOfYear = (int)(daysSinceEpoch - farvardin1); // 0-based
            if (dayOfYear < 216) { // Compute 0-based month
                month = dayOfYear / 31;
            } else {
                month = (dayOfYear - 6) / 30;
            }
            dayOfMonth = dayOfYear - MONTH_COUNT[month][2] + 1;
            ++dayOfYear; // Make it 1-based now
        
            internalSet(ERA, 0);
            internalSet(YEAR, year);
            internalSet(EXTENDED_YEAR, year);
            internalSet(MONTH, month);
            internalSet(DAY_OF_MONTH, dayOfMonth);
            internalSet(DAY_OF_YEAR, dayOfYear);       
        }

}
