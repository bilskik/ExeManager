package data;

import main.exemanager.Alerts;

import java.io.FileWriter;
import java.io.IOException;
import java.io.SerializablePermission;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonWrite  {
    public static void writeToJson() {
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
    private static JSONObject jsonPut() {
        JSONObject UserOuterJson = new JSONObject();
        JSONObject BodyPartsInnerJson = new JSONObject();

        DataManager dataManager = DataManager.getInstance();
        BodyPartsData [] bodyPartsData = dataManager.getArrBodyPart();
        for(int i=0; i<bodyPartsData.length; i++) {
            List<ThatExercise> list = bodyPartsData[i].getList();
            JSONArray SpecifiedExeInnerJson = new JSONArray();
            for(var iter : list) {
                JSONObject ExeStatsInnerJson = new JSONObject();
                ExeStatsInnerJson.put("NazwaCwiczenia", iter.getName());
                ExeStatsInnerJson.put("Serie", iter.getSeries());
                ExeStatsInnerJson.put("Powtorzenia", iter.getAmount());
                ExeStatsInnerJson.put("Kilogramy", iter.getWeight());
                ExeStatsInnerJson.put("Czasprzerwy", iter.getTime());
                SpecifiedExeInnerJson.add(ExeStatsInnerJson);

            }
            BodyPartsInnerJson.put(bodyPartsData[i].name,SpecifiedExeInnerJson);
        }

        UserName userName = UserName.getInstance();
        String name = userName.getName();
        UserOuterJson.put(name, BodyPartsInnerJson);
        return UserOuterJson;
    }

}
