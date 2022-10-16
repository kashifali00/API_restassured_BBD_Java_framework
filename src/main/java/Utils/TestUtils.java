package Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TestUtils {

    Random rand = new Random();
    int number;

    public String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public int randomNumber() {
        rand = new Random();
        number = rand.nextInt(1000);
        return number;
    }
}
