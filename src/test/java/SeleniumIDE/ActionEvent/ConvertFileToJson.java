package SeleniumIDE.ActionEvent;

import SeleniumIDE.SeleniumObject.TestCase;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.contains;

import java.io.FileReader;

public class ConvertFileToJson {
    public TestCase convertFileDataToObject(String fileName){
        TestCase testCase = new TestCase();
        try{
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(new FileReader(contains.folderSeleniumIde+fileName));
            testCase = new Gson().fromJson(String.valueOf(json),TestCase.class);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return testCase;
    }
}
