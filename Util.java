import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

class Util {
    static Random r = new Random();

    public static String getRandomName() {
        char[] vogal = { 97, 101, 105, 111, 117 };
        char[] consoant = { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w',
                'x', 'y', 'z' };
        boolean first = r.nextBoolean();
        char initial = first ? vogal[r.nextInt(vogal.length)] : consoant[r.nextInt(consoant.length)];
        initial -= 32;
        String name = "" + initial;
        int size = r.nextInt(8) + 1;
        for (int i = 0; i < size; i++) {
            char next = (!first ? i % 2 == 0 : i % 2 != 0) ? vogal[r.nextInt(vogal.length)]
                    : consoant[r.nextInt(consoant.length)];
            name += next;
        }
        return name;
    }

    public static int getDice(int max) {
        return r.nextInt(max) + 1;
    }

    public static int[] getDice(int max, int quant) {
        int q = (quant == 0 || quant < 0) ? 1 : quant;
        int[] dices = new int[q];
        for (int i = 0; i < dices.length; i++) {
            dices[i] = r.nextInt(max) + 1;
        }
        return dices;
    }

    public static String getDiceText(int max, int quant) {
        int[] dices = getDice(max, quant);
        String text = "[";
        for (int dice : dices) {
            text += (text.length() == 1 ? "" : ",") + dice;
        }
        text += "]";
        return text;
    }

    public static Calendar getRandomCalendar(int maxAge) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -getDice(maxAge));
        cal.set(Calendar.MONTH, getDice(12));
        cal.set(Calendar.DAY_OF_MONTH, getDice(31));
        return cal;
    }

    public static int getAge(Date birth) {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birth));
        int d2 = Integer.parseInt(formatter.format(new Date()));
        int age = (d2 - d1) / 10000;
        return age;
    }

    public static int getAge(String birth) throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(birth);
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(date));
        int d2 = Integer.parseInt(formatter.format(new Date()));
        int age = (d2 - d1) / 10000;
        return age;
    }
}