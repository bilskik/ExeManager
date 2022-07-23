package data;

import main.exemanager.Alerts;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class JsonWrite  {
    public void writeToJson() {
        JSONObject jsonObject = jsonPut();
        try {
            FileWriter file = new FileWriter("D:\\projekty_java\\ExeManager\\src\\main\\FIle's\\test.json");
            file.write(jsonObject.toString());
            file.close();

        } catch (IOException e) {
            Alerts alerts = new Alerts("FileNotFound");
            alerts.displayError();
            throw new RuntimeException(e);
        }
    }
    private JSONObject jsonPut() {
        JSONObject UserOuterJson = new JSONObject();
        JSONObject BodyPartsInnerJson = new JSONObject();
        JSONObject [] SpecifiedExeInnerJson = new JSONObject[100];
        JSONObject [] ExeStatsInnerJson = new JSONObject[100];
        int j = 0;
        DataManager dataManager = DataManager.getInstance();
        BodyPartsData [] bodyPartsData = dataManager.getArrBodyPart();
        for(int i=0; i<bodyPartsData.length; i++) {
            List<ThatExercise> list = bodyPartsData[i].getList();
            System.out.println(bodyPartsData[i].name);
            for(var iter : list) {
                System.out.println("name " + iter.name);
                ExeStatsInnerJson[j] = new JSONObject();
                SpecifiedExeInnerJson[j] = new JSONObject();
                ExeStatsInnerJson[j].put("Serie", iter.getSeries());
                ExeStatsInnerJson[j].put("Powtorzenia", iter.getAmount());
                ExeStatsInnerJson[j].put("Kilogramy", iter.getWeight());
                ExeStatsInnerJson[j].put("Czasprzerwy", iter.getTime());
                SpecifiedExeInnerJson[j].put(iter.name,ExeStatsInnerJson[j]);
                j++;
                //ExeStatsInnerJson = removeJsonDataStats(ExeStatsInnerJson);
            }
            BodyPartsInnerJson.put(bodyPartsData[i].name,SpecifiedExeInnerJson[j]);
            //SpecifiedExeInnerJson = removeJsonDataSpecifiedExe(SpecifiedExeInnerJson, list);
        }

        UserName userName = UserName.getInstance();
        String name = userName.getName();
        UserOuterJson.put(name, BodyPartsInnerJson);
        return UserOuterJson;
    }
    private JSONObject removeJsonDataStats(JSONObject ExeStatsInnerJson) {
        ExeStatsInnerJson.remove("Serie");
        ExeStatsInnerJson.remove("Powtorzenia");
        ExeStatsInnerJson.remove("Kilogramy");
        ExeStatsInnerJson.remove("Czasprzerwy");
        return ExeStatsInnerJson;
    }
    private JSONObject removeJsonDataSpecifiedExe(JSONObject jsonObject, List<ThatExercise> list) {
        for(var i : list) {
            jsonObject.remove(i.name);
        }
        return jsonObject;
    }
}
