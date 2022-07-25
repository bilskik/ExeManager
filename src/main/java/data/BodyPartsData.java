package data;

import java.util.ArrayList;
import java.util.List;

public class BodyPartsData {
    String name;
    List<ThatExercise> list;
    public void add(ThatExercise thatExercise) {
        list.add(thatExercise);
    }
    public void remove(String value) {
        int index = 0;
        for(var thatExeElement : list) {
            if(thatExeElement.name.equals(value))
                break;
            index++;
        }
        list.remove(index);
    }
    public List<ThatExercise> getList() {
        return list;
    }
    public String getName() {
        return name;
    }
    public void setList() {
        list = new ArrayList<>();
    }
    public void setList(List<ThatExercise> list) {
        this.list = list;
    }
}
