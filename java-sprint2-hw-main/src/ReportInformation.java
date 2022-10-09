import java.util.ArrayList;
import java.util.HashMap;

public class ReportInformation {
    YearlyReport yearR = new YearlyReport();
    void monthReportInformation(HashMap<Integer, ArrayList<MonthlyReport>> MonthlyReport) {
        if (DataReconciliation.ValidationM(MonthlyReport)) {
            for (int i = 0; i < MonthlyReport.size(); i++) {
                System.out.println(nameMonth(i));
                double maxIncome = 0;
                String nameMaxIncome = "";
                double maxExpense = 0;
                String nameMaxExpense = "";
                for (int j = 0; j < MonthlyReport.get(i + 1).size(); j++) {
                    MonthlyReport result = MonthlyReport.get(i + 1).get(j);
                    if (!result.is_expense) {
                        double resultSumIncome = result.sum_of_one * result.quantity;
                        if (maxIncome < resultSumIncome) {
                            maxIncome = resultSumIncome;
                            nameMaxIncome = result.item_name;
                        }
                    } else {
                        double resultSumExpense = result.sum_of_one * result.quantity;
                        if (maxExpense < resultSumExpense) {
                            maxExpense = resultSumExpense;
                            nameMaxExpense = result.item_name;
                        }
                    }
                }
                System.out.println("Самый прибыльный товар: " + nameMaxIncome + ". Прибыль составила: " + maxIncome);
                System.out.println("Самая большая трата: " + nameMaxExpense + ". Трата составила: " + maxExpense);
            }
        }
    }

    void yearReportInformation(HashMap<Integer, ArrayList<YearlyReport>> YearlyReport) {
        if (DataReconciliation.ValidationY(YearlyReport)) {

            System.out.println(yearR.getKey() + " год.");
            double Income = 0;
            double Expenses = 0;
            double AmountOfExpenses = 0;
            double AmountOfIncome = 0;
            int testMonth = 0;
            for (int j = 0; j < YearlyReport.get(yearR.getKey()).size(); j++) {
                YearlyReport result = YearlyReport.get(yearR.getKey()).get(j);
                testMonth++;
                if (!result.is_expense) {
                    Income = result.amount;
                    AmountOfIncome += result.amount;
                } else {
                    Expenses = result.amount;
                    AmountOfExpenses += result.amount;
                }
                if (testMonth % 2 == 0) {
                    System.out.println("Прибыль за " + nameMonth(j / 2) + ": " + (Income - Expenses));
                }
            }
            System.out.println("Средний расход за все месяцы в году: " + (AmountOfExpenses / YearlyReport.get(yearR.getKey()).size()));
            System.out.println("Средний доход за все месяцы в году: " + (AmountOfIncome / YearlyReport.get(yearR.getKey()).size()));
        }
    }

    public String nameMonth(int month) {
        String nameMonth = "";
        switch (month) {
            case (0):
                nameMonth = "Январь";
                break;
            case (1):
                nameMonth = "Февраль";
                break;
            case (2):
                nameMonth = "Март";
                break;
            case (3):
                nameMonth = "Апрель";
                break;
            case (4):
                nameMonth = "Май";
                break;
            case (5):
                nameMonth = "Июнь";
                break;
            case (6):
                nameMonth = "Июль";
                break;
            case (7):
                nameMonth = "Август";
                break;
            case (8):
                nameMonth = "Сентябрь";
                break;
            case (9):
                nameMonth = "Октябрь";
                break;
            case (10):
                nameMonth = "Ноябрь";
                break;
            case (11):
                nameMonth = "Декабрь";
                break;
        }
        return nameMonth;
    }
}
