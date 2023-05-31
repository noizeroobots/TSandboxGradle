package delegats;

public class ConvertData {

    public String convertData(String date){
        return date.substring(0, date.length() - 6) + "Z";
    }
}