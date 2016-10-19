import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import model.Person;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ExampleConvertExclude {

    private static class MyExclusionStrategy implements ExclusionStrategy {

        @Override
        public boolean shouldSkipClass(Class<?> arg0) {
            return false;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return (f.getDeclaringClass() == Person.class && f.getName().equals("changes"));
        }

    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<Person>();
        list.add(createPerson("FirstName", "Flexible and extensible", 30));
        list.add(createPerson("Frist DI", "@Inject as programming mode", 30));
        list.add(createPerson("Frist OSGi", "Services", 30));
        list.add(createPerson("Frist SWT", "Widgets", 30));
        list.add(createPerson("Frist JFace", "Especially Viewers!", 30));
        list.add(createPerson("Frist CSS Styling", "Style your application", 30));
        list.add(createPerson("Frist Eclipse services", "Selection, model, Part", 30));
        list.add(createPerson("Frist Renderer", "Different UI toolkit", 30));
        list.add(createPerson("Frist Compatibility Layer", "Run Eclipse 3.x", 30));

        // Add .excludeFieldsWithoutExposeAnnotation() to exclude fields not annotated with @Expose
        Gson gson = new GsonBuilder().setPrettyPrinting().setExclusionStrategies(new MyExclusionStrategy()).excludeFieldsWithoutExposeAnnotation()
                .create();

        Type type = new TypeToken<List<Person>>() {
        }.getType();
        String json = gson.toJson(list, type);
        try {
            Files.write(Paths.get("Person.txt"), json.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get("Person.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(content);
    }

    private static Person createPerson(String firstName, String lastName, int age) {
        return new Person(firstName, lastName, age);
    }

}
