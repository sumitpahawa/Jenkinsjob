package core.managers.filemanager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


//HOW TO USE
//public class Test {
//	public static final void main(String[] args) {
//		JSONReader db = new JSONReader("C:\\Users\\12063\\Downloads\\Test\\src\\deviceDetails.json");
//
//		try {
//			System.out.println(db.getJSONValue(null, null, "testField"));
//			System.out.println(db.getJSONValue("webPrimary", null, "location"));
//			System.out.println(db.getJSONValue("webPrimary", "messagingApp", "model"));
//		} catch(NullPointerException e) {
////            e.printStackTrace();
//		}
//	}
//}

/**
 * JSONReader is customized class for Reading JSON files
 *
 * @author Sumit
 */
public class JSONReader {

    private JSONObject JsonObject;

    /**
     * JSONReader(JSONObject obj) is used  when there is nested object in Json file
     *
     * @param JsonObject value
     */
    public JSONReader(JSONObject JsonObject) {
        this.JsonObject = JsonObject;
    }

    /**
     * JSONReader(String a) initialize JsonObject to read
     *
     * @param JsonPath to the file location
     */
    public JSONReader(String JsonPath) {
        JSONParser parser = new JSONParser();

        try {

            ClassLoader classLoader = this.getClass().getClassLoader();
            // Getting resource(File) from class loader
            File jsonfile = new File(classLoader.getResource(JsonPath).getFile());
            System.out.println(jsonfile.toString());

            Object obj = parser.parse(new FileReader(jsonfile));
            JsonObject = (JSONObject) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * to get generic object from json
     *
     * @param attribute value
     */
    private Object get(String attribute) {
        Object obj = null;
        try {
            obj = JsonObject.get(attribute);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * return String from JSON
     *
     * @param attribute value
     */
    private String getString(String attribute) throws NullPointerException {
        String value = null;
        try {
            value = (String) JsonObject.get(attribute).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * return Array from JSON
     *
     * @param attribute value
     */
    private Object[] getArray(String attribute) {
        Object[] tempArray;
        try {
            JSONArray Jarray = (JSONArray) get(attribute);
            tempArray = Jarray.toArray();
        } catch (Exception e) {
            tempArray = null;
            e.printStackTrace();
        }
        return tempArray;
    }


    /**
     * return JSONReader obj for nested jsonObject from JSON
     *
     * @param attribute value
     */
    private JSONReader getJsonObject(String attribute) {
        JSONReader jsonReader;
        try {
            jsonReader = new JSONReader((JSONObject) JsonObject.get(attribute));
        } catch (Exception e) {
            jsonReader = null;
            e.printStackTrace();
        }

        return jsonReader;
    }

    public String getJSONValue(String field) {
        //if we want to get just the field, outside of object
        return getString(field);
    }

    public String getJSONValue(String object, String field) {
        //if we want to get the value of parent object field
        return getJsonObject(object).getString(field);
    }


    public String getJSONValue(String object, String childObject, String field) {
        //get a value inside parent -> apps -> child -> field
        return getJsonObject(object).getJsonObject("apps").getJsonObject(childObject).getString(field);

    }
}
