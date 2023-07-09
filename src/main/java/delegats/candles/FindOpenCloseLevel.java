package delegats.candles;

public class FindOpenCloseLevel {

    /**
     * Метод возвращает цену
     */
    public String findPrice(String units, String nano) {
        if (nano.length() == 9) {
            String buffer = units + "." + nano;
            return buffer.substring(0, buffer.length() - 6);
        } if (nano.length() == 8) {
            String buffer = units + "." + nano;
            return buffer.substring(0, buffer.length() - 5);
        } else {
            return units + ".000";
        }
    }
}
//"nano": 935000000
//"nano": 25000000