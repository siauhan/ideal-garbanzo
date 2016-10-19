package model;

import com.google.gson.annotations.Expose;

public class Person {

    public Person(String firstName, String lastName, int age) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Expose
    String firstName;
    String lastName;
    int age;
}
// Main Class
// public class App
// {
// public static void main( String[] args )
// {
// Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
// Person pers=new Person("Venkat","Gutta",30);
// System.out.println("JAVA to JSON:"+gson.toJson(pers));
//
// }
// }

