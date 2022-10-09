import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    protected Integer key = 2021;
    protected boolean is_expense;
    protected double amount;
    protected int month;

    YearlyReport() {
    }

    YearlyReport(boolean is_expense, double amount, int month) {
        this.is_expense = is_expense;
        this.amount = amount;
        this.month = month;
    }

  HashMap <Integer, ArrayList<YearlyReport>> readFileContentsOrNull() {
        String result;
        HashMap<Integer, ArrayList<YearlyReport>> yearReport = new HashMap<>();
        try {
            result = Files.readString(Path.of("java-sprint2-hw-main\\resources\\y." + getKey() + ".csv")); // считали файл и положили в result

            String[] lines = result.split("\n");
            boolean is_expense = false;
            double quantity = 0.0;
            int sum_of_one = 0;

            ArrayList<YearlyReport> list = new ArrayList<>();
            for (int j = 1; j < lines.length; j++) {
                String[] lineContents = lines[j].split(","); // разделили файл по строкам (по запятым)

                for (int a = 0; a < lineContents.length; a++) {
                    if (a == 0) {
                        sum_of_one = Integer.parseInt(lineContents[a]);
                    } else if (a == 1) {
                        quantity = Double.parseDouble(lineContents[a]);
                    } else if (a == 2) {
                        is_expense = Boolean.parseBoolean(lineContents[a]);
                    }
                }
                YearlyReport yearlyReport = new YearlyReport(is_expense, quantity, sum_of_one);

                list.add(yearlyReport);
            }
            yearReport.put(getKey(), list);
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        } return yearReport;
    }

    public int getKey() {
        return key;
    }
}
