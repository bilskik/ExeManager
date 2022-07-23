package data;

import main.exemanager.Alerts;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
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
        DataManager dataManager = DataManager.getInstance();
        BodyPartsData [] bodyPartsData = dataManager.getArrBodyPart();
        for(int i=0; i<bodyPartsData.length; i++) {
            List<ThatExercise> list = bodyPartsData[i].getList();
            System.out.println(bodyPartsData[i].name);
            JSONObject SpecifiedExeInnerJson = new JSONObject();
            for(var iter : list) {
                JSONObject ExeStatsInnerJson = new JSONObject();
                ExeStatsInnerJson.put("Serie", iter.getSeries());
                ExeStatsInnerJson.put("Powtorzenia", iter.getAmount());
                ExeStatsInnerJson.put("Kilogramy", iter.getWeight());
                ExeStatsInnerJson.put("Czasprzerwy", iter.getTime());
                SpecifiedExeInnerJson.put(iter.name,ExeStatsInnerJson);
            }
            BodyPartsInnerJson.put(bodyPartsData[i].name,SpecifiedExeInnerJson);
        }

        UserName userName = UserName.getInstance();
        String name = userName.getName();
        UserOuterJson.put(name, BodyPartsInnerJson);
        return UserOuterJson;
    }

}
