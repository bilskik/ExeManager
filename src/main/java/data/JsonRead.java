package data;

import main.exemanager.Alerts;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonRead {
    public static void readFromJson() {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new
                    FileReader("D:\\projekty_java\\ExeManager\\src\\main\\FIle's\\test.json"));
            reader(jsonObject);

        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
            Alerts alerts = new Alerts("FileNotFound");
            alerts.displayError();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
    private static void reader(JSONObject jsonWholeFileObject) {
        DataManager dataManager = DataManager.getInstance();
        BodyPartsData [] bodyPartsData = dataManager.getArrBodyPart();
        UserName userName = UserName.getInstance();
        JSONObject jsonUser = (JSONObject)jsonWholeFileObject.get(userName.name);
        for(int i=0; i<6; i++) {
            List<ThatExercise> list = new ArrayList<>();
            JSONArray exeArr = (JSONArray)jsonUser.get(bodyPartsData[i].name);
            for(int j=0; j<exeArr.size(); j++) {
                if(exeArr.get(i) == null)
                    continue;
                JSONObject specifiedExe = (JSONObject) exeArr.get(i);
                ThatExercise thatExercise = setThatExerciseClass(specifiedExe);
                list.add(thatExercise);
            }
            bodyPartsData[i].setList(list);
        }

    }
    private static ThatExercise setThatExerciseClass(JSONObject specifiedExe) {
        double [] value = new double[4];
        Arrays.fill(value,0.0);
        if(specifiedExe.get("Serie") != null)
            value[0] = (Double)specifiedExe.get("Serie");
        if(specifiedExe.get("Powtorzenia") != null)
            value[1] = (Double)specifiedExe.get("Powtorzenia");
        if(specifiedExe.get("Kilogramy") != null)
            value[2] = (Double)specifiedExe.get("Kilogramy");
        if(specifiedExe.get("CzasPrzerwy") != null)
            value[3] = (Double)specifiedExe.get("CzasPrzerwy");
        return new ThatExercise((String)specifiedExe.get("NazwaCwiczenia"),value[0],value[1],value[2],value[3]);
    }
}
