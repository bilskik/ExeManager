package main.exemanager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Reader {
    Map<String,String> data;

    public Map<String,String> read() {
        String path = "D:\\projekty_java\\ExeManager\\src\\main\\FIle's\\Logins\\login.txt";
        File file = new File(path);
        data = new HashMap<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] split = s.split(":");
                data.put(split[0], split[1]);
            }
            return data;
        }
        catch (IOException e) {
            Alerts alerts = new Alerts("FileNotFound");
            alerts.displayError();
        }
        return null;
    }

}
