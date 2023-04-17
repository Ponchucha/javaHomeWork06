import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
Создать множество ноутбуков.
Написать метод, который будет запрашивать у пользователя критерий фильтрации и выведет ноутбуки, отвечающие фильтру.
NoteBook notebook1 = new NoteBook
NoteBook notebook2 = new NoteBook
NoteBook notebook3 = new NoteBook
NoteBook notebook4 = new NoteBook
NoteBook notebook5 = new NoteBook

Например: “Введите цифру, соответствующую необходимому критерию:
1 - ОЗУ
2 - Объем ЖД
3 - Операционная система
4 - Цвет

Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.

Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.

Класс сделать в отдельном файле

приветствие
Выбор параметра
выбор конкретнее
вывод подходящих */

/**
 * homework
 */
public class homework {

    public static void main(String[] args) {
        File file = new File("db.txt");
        String device = "";
        ArrayList <Laptop> catalogue  = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while((device = reader.readLine()) != null){
                catalogue.add(new Laptop(device));
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("error");
        }
        // for (Laptop laptop : catalogue) {
        //     System.out.println(laptop.getInfo());
        // }   
        Scanner input = new Scanner(System.in);
        int choice = 0;
        int min = 0;
        int max = 0;
        ArrayList <Laptop> result = new ArrayList<>();
        Map <String, Integer> choiceMap = new LinkedHashMap<>();
        boolean end = false;
        System.out.println("Приветствуем в нашем магазине ноутбуков! У нас огромный выбор из " + catalogue.size() + " моделей!");
        while (!end){
            System.out.println("\nЧтобы не утомиться, читая длинный список товаров,\nвведите цифру, соответствующую необходимому критерию:"
             + "\n1 - ОЗУ\n2 - Объем ЖД\n3 - Операционная система\n4 - Цвет\n5 - Покинуть магазин");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Введите минимальное и максимальное значение ОЗУ:\nОт: ");
                    min = input.nextInt();
                    System.out.print("До: ");
                    max = input.nextInt();
                    for (Laptop laptop : catalogue) {
                        if(laptop.getRAM() >= min && laptop.getRAM() <= max){
                            result.add(laptop);
                        }
                    }
                    break;
                case 2:
                    System.out.print("Введите минимальное и максимальное значение объёма диска (в Гб):\nОт: ");
                    min = input.nextInt();
                    System.out.print("До: ");
                    max = input.nextInt();
                    for (Laptop laptop : catalogue) {
                        if(laptop.getMemory() >= min && laptop.getMemory() <= max){
                            result.add(laptop);
                        }
                    }
                    break;
                case 3:
                    int i = 1;
                    for (Laptop laptop : catalogue) {
                        if(!choiceMap.keySet().contains(laptop.getOS())){
                            choiceMap.put(laptop.getOS(), i);
                            i++;
                        }
                    }
                    System.out.println("Выберите подходящую ОС из списка: ");
                    for (var opSys : choiceMap.entrySet()) {
                        System.out.println(opSys.getValue() + " - " + opSys.getKey());
                    }
                    choice = input.nextInt();
                    String os = "";
                    for (var opSys : choiceMap.entrySet()) {
                        if (opSys.getValue() == choice){
                            os = opSys.getKey();
                            choiceMap.clear(); 
                            break;
                        }
                    }
                    for (Laptop laptop : catalogue) {
                        if(laptop.getOS().equals(os)){
                            result.add(laptop);
                        }
                    }
                    break;
                case 4:
                    int j = 1;
                    for (Laptop laptop : catalogue) {
                        if(!choiceMap.keySet().contains(laptop.getColor())){
                            choiceMap.put(laptop.getColor(), j);
                            j++;
                        }
                    }
                    System.out.println("Выберите подходящий цвет из списка: ");
                    for (var color : choiceMap.entrySet()) {
                        System.out.println(color.getValue() + " - " + color.getKey());
                    }
                    choice = input.nextInt();
                    String coloString = "";
                    for (var color : choiceMap.entrySet()) {
                        if (color.getValue() == choice){
                            coloString = color.getKey();
                            choiceMap.clear(); 
                            break;
                        }
                    }
                    for (Laptop laptop : catalogue) {
                        if(laptop.getColor().equals(coloString)){
                            result.add(laptop);
                        }
                    }
                    break;
                case 5:
                    end = true;
                    break;            
                default:
                    System.out.println("Введите число от 1 до 5.");
                    break;
            }
            if(!end){
                if(!result.isEmpty()){
                    if(result.size() > 1){
                    for (int i = 0; i < result.size(); i++) {
                        System.out.println((i+1) + " - " + result.get(i).getInfo());
                    }
                    System.out.println("Выберите подходящую модель из списка(число от 1 до " + result.size() + "): ");
                    choice = input.nextInt();
                    System.out.println("\nВы выбрали модель " + result.get(choice-1).getModel() + "\nС Вас " + result.get(choice-1).priceOutput() + "рублей\n\n");

                    }
                    else{
                        System.out.println("Вам подходит модель:\n" + result.get(0).getInfo() + "\nС Вас " + result.get(0).priceOutput() + " рублей\n\n");
                    }
                    result.clear();                
                }
                else{
                    System.out.println("К сожалению, у нас нет подходящего ноутбука. Попробуйте изменить параметры поиска");
                }                
            }
            else{
                System.out.println("Прощайте!");
            }
        }
            input.close();
    }
}