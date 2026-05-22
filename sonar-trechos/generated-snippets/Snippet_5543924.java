public class TestDateUtil {

    @Test
    public void getJavaDate_InvalidValue() {
        double dateValue = -1;
        TimeZone tz = LocaleUtil.getUserTimeZone();
        boolean use1904windowing = false;
        boolean roundSeconds = false;

        assertEquals(null, DateUtil.getJavaDate(dateValue));
        assertEquals(null, DateUtil.getJavaDate(dateValue, tz));
        assertEquals(null, DateUtil.getJavaDate(dateValue, use1904windowing));
        assertEquals(null, DateUtil.getJavaDate(dateValue, use1904windowing, tz));
        assertEquals(null, DateUtil.getJavaDate(dateValue, use1904windowing, tz, roundSeconds));
    }

    @Test
    public void getJavaDate_ValidValue() {
        double dateValue = 0;
        TimeZone tz = LocaleUtil.getUserTimeZone();
        boolean use1904windowing = false;
        boolean roundSeconds = false;

        Calendar calendar = LocaleUtil.getLocaleCalendar(1900, 0, 0);
        Date date = calendar.getTime();

        assertEquals(date, DateUtil.getJavaDate(dateValue));
        assertEquals(date, DateUtil.getJavaDate(dateValue, tz));
        assertEquals(date, DateUtil.getJavaDate(dateValue, use1904windowing));
        assertEquals(date, DateUtil.getJavaDate(dateValue, use1904windowing, tz));
        assertEquals(date, DateUtil.getJavaDate(dateValue, use1904windowing, tz, roundSeconds));
    }

    @Test
    public void getJavaCalendar_InvalidValue() {
        double dateValue = -1;
        TimeZone tz = LocaleUtil.getUserTimeZone();
        boolean use1904windowing = false;
        boolean roundSeconds = false;

        assertEquals(null, DateUtil.getJavaCalendar(dateValue));
        assertEquals(null, DateUtil.getJavaCalendar(dateValue, use1904windowing));
        assertEquals(null, DateUtil.getJavaCalendar(dateValue, use1904windowing, tz));
        assertEquals(null, DateUtil.getJavaCalendar(dateValue, use1904windowing, tz, roundSeconds));
    }

    @Test
    public void getJavaCalendar_ValidValue() {
        double dateValue = 0;
        TimeZone tz = LocaleUtil.getUserTimeZone();
        boolean use1904windowing = false;
        boolean roundSeconds = false;

        Calendar expCal = LocaleUtil.getLocaleCalendar(1900, 0, 0);

        Calendar[] actCal = {
                DateUtil.getJavaCalendar(dateValue),
                DateUtil.getJavaCalendar(dateValue, use1904windowing),
                DateUtil.getJavaCalendar(dateValue, use1904windowing, tz),
                DateUtil.getJavaCalendar(dateValue, use1904windowing, tz, roundSeconds)
        };
        assertEquals(expCal, actCal[0]);
        assertEquals(expCal, actCal[1]);
        assertEquals(expCal, actCal[2]);
        assertEquals(expCal, actCal[3]);
    }
    
    @Test
    public void isADateFormat() {
        // Cell content 2016-12-8 as an example
        // Cell show "12/8/2016"
        assertTrue(DateUtil.isADateFormat(14, "m/d/yy"));
        // Cell show "Thursday, December 8, 2016"
        assertTrue(DateUtil.isADateFormat(182, "[$-F800]dddd\\,\\ mmmm\\ dd\\,\\ yyyy"));
        // Cell show "12/8"
        assertTrue(DateUtil.isADateFormat(183, "m/d;@"));
        // Cell show "12/08/16"
        assertTrue(DateUtil.isADateFormat(184, "mm/dd/yy;@"));
        // Cell show "8-Dec-16"
        assertTrue(DateUtil.isADateFormat(185, "[$-409]d\\-mmm\\-yy;@"));
        // Cell show "D-16"
        assertTrue(DateUtil.isADateFormat(186, "[$-409]mmmmm\\-yy;@"));

        // Cell show "2016年12月8日"
        assertTrue(DateUtil.isADateFormat(165, "yyyy\"\u5e74\"m\"\u6708\"d\"\u65e5\";@"));
        // Cell show "2016年12月"
        assertTrue(DateUtil.isADateFormat(164, "yyyy\"\u5e74\"m\"\u6708\";@"));
        // Cell show "12月8日"
        assertTrue(DateUtil.isADateFormat(168, "m\"\u6708\"d\"\u65e5\";@"));
        // Cell show "十二月八日"
        assertTrue(DateUtil.isADateFormat(181, "[DBNum1][$-404]m\"\u6708\"d\"\u65e5\";@"));
        // Cell show "贰零壹陆年壹拾贰月捌日"
        assertTrue(DateUtil.isADateFormat(177, "[DBNum2][$-804]yyyy\"\u5e74\"m\"\u6708\"d\"\u65e5\";@"));
        // Cell show "２０１６年１２月８日"
        assertTrue(DateUtil.isADateFormat(178, "[DBNum3][$-804]yyyy\"\u5e74\"m\"\u6708\"d\"\u65e5\";@"));
    }
}
