public class Snippet__6536158 {

    public SunZodiac getZodiac(Calendar calendar) {
            int year = calendar.get(Calendar.YEAR);
            List<SunZodiac> zodiacs = zodiacsByYear.get(year);
            if (zodiacs == null) {
                zodiacs = calculateZodiacs(year);
                zodiacsByYear.clear();
                zodiacsByYear.put(year, zodiacs);
            }

            for (SunZodiac zodiac : zodiacs) {
                if (zodiac.isValid(calendar)) {
                    return zodiac;
                }
            }

            return null;
        }

}
