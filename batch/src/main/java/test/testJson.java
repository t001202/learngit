package test;


import domain.Person;
import net.sf.json.JSONObject;

/**
 * Created by alibb on 2017/9/12.
 */
public class testJson {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","111");
        jsonObject.put("name","李磊");
        jsonObject.put("age","23");

        Person o = (Person)JSONObject.toBean(jsonObject, Person.class);

        System.out.println(o);
    }
}
