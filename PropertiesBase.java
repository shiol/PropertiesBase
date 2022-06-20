import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

class PropertiesBase {
    public static void main(String[] args) throws IOException, ParseException {
        Properties pNomes = new Properties();
        FileInputStream inNomes = new FileInputStream("cliente-nome.properties");
        FileOutputStream outNomes = new FileOutputStream("cliente-nome.properties");
        Properties pNascimentos = new Properties();
        FileInputStream inNascimentos = new FileInputStream("cliente-nascimento.properties");
        FileOutputStream outNascimentos = new FileOutputStream("cliente-nascimento.properties");

        // saving
        for (int i = 0; i < 10; i++) {
            String id = UUID.randomUUID().toString();
            pNomes.setProperty(id, Util.getRandomName());

            Calendar birth = Util.getRandomCalendar(100);
            pNascimentos.setProperty(id, new SimpleDateFormat("dd/MM/yyyy").format(birth.getTime()));
        }
        pNomes.store(outNomes, "names");
        pNascimentos.store(outNascimentos, "nascimentos");

        pNomes.load(inNomes);
        pNascimentos.load(inNascimentos);
        
        Set<Entry<Object, Object>> names = pNomes.entrySet();
        for (Entry<Object, Object> entry : names) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println(key + ":" + value);
        }

        Set<Entry<Object, Object>> nascimentos = pNascimentos.entrySet();
        for (Entry<Object, Object> entry : nascimentos) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            
            int age = Util.getAge(value);
            System.out.println(key + ":" + value + " (" + age + " years)");
        }

        inNomes.close();
        outNomes.close();
        inNascimentos.close();
        outNascimentos.close();
    }
}