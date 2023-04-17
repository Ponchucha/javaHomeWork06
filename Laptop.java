import java.util.ArrayList;

public class Laptop {
    private String model;
    private int ram;
    private int memory;
    private String operatingSystem;
    private String color;
    private double price;
    private boolean condition;

    public String getInfo(){
        return String.format("%s:  %d Gb RAM, %d Gb, %s, %s, %.2f рублей", model, ram, memory, operatingSystem, color, price);
    }
    public Laptop(String cataloguEntry){
        String[] info = cataloguEntry.split("#");
        this.model = info[0];
        this.ram = Integer.parseInt(info[1]);
        this.memory = Integer.parseInt(info[2]);
        this.operatingSystem = info[3];
        this.color = info[4];
        this.price = Double.parseDouble(info[5]);
        this.condition = true;
    }
    public void reInsttallOS(String newOs){
        this.operatingSystem = newOs;
    }
    public void setCondition(boolean condition){
        this.condition = condition;
    }
    public void Repair(){
        condition = true;
        System.out.printf("Ноутбук %s теперь раблтает нормально", model);
    }
    public int getRAM(){
        return this.ram;
    }
    public int getMemory(){
        return this.memory;
    }
    public String getOS(){
        return this.operatingSystem;
    }
    public String getColor(){
        return this.color;
    }
    public String getModel(){
        return this.model;
    }
    public String priceOutput(){
        return String.format("%.2f", this.price);
    }
}
