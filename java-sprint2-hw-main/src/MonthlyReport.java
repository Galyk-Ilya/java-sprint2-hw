import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    String item_name;
    boolean is_expense;
    double quantity;
    int sum_of_one;

    public MonthlyReport() {
    }

    public MonthlyReport(String item_name, boolean is_expense, double quantity, int sum_of_one) {
        this.item_name = item_name;
        this.is_expense = is_expense;
        this.quantity = quantity;
        this.sum_of_one = sum_of_one;
    }

    public HashMap<Integer, ArrayList<MonthlyReport>> readFileContentsOrNull() {
        String result;
        HashMap<Integer, ArrayList<MonthlyReport>> monthReport = new HashMap<>();
        for (int i = 1; i < 4; i++) {
            try {

                result = Files.readString(Path.of("java-sprint2-hw-main" + File.separator + "resources" + File.separator + "m.20210" + i + ".csv"));
                //Круто, все поменял конструкция "//" и "/n" и т.д. могут не работать на других системах как понял.
                //p.s. "java-sprint2-hw-main" не получилось заменить точкой("."), не пропускал путь, что-то нужно было добавить перед точкой?
                String[] lines = result.split(System.lineSeparator());
                String item_name = "";
                boolean is_expense = false;
                double quantity = 0.0;
                int sum_of_one = 0;

                ArrayList<MonthlyReport> list = new ArrayList<>();
                for (int j = 1; j < lines.length; j++) {
                    String[] lineContents = lines[j].split(",");

                    for (int a = 0; a < lineContents.length; a++) {
                        if (a == 0) {
                            item_name = lineContents[a];
                        } else if (a == 1) {
                            is_expense = Boolean.parseBoolean(lineContents[a]);
                        } else if (a == 2) {
                            quantity = Double.parseDouble(lineContents[a]);
                        } else if (a == 3) {
                            sum_of_one = Integer.parseInt(lineContents[a]);
                        }
                    }
                    MonthlyReport monthlyReport = new MonthlyReport(item_name, is_expense, quantity, sum_of_one);

                    list.add(monthlyReport);
                }
                monthReport.put(i, list);
            } catch (IOException e) {
                System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
                return null;
            }
        }
        return monthReport;
    }
}