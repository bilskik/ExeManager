package data;

public class ThatExercise {
    public String name;
    double series = 0;
    double amount= 0;
    double weight = 0;
    double time = 0;
    public ThatExercise(String name, int series, int amount, int weight, int time) {
        this.name = name;
        this.series = series;
        this.amount = amount;
        this.weight = weight;
        this.time = time;

    }
    public ThatExercise(String name) {
        this.name = name;
    }

    public void setSeries(double series) {
        this.series = series;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void setTime(double time) {
        this.time = time;
    }
    public String getName() {
        return name;
    }
    public double getSeries() {
        return series;
    }
    public double getAmount() {
        return amount;
    }
    public double getWeight() {
        return weight;
    }
    public double getTime() {
        return time;
    }

}
