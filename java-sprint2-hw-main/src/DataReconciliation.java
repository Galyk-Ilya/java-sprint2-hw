import java.util.ArrayList;
import java.util.HashMap;

public class DataReconciliation {
    YearlyReport yearR = new YearlyReport();
    ArrayList<ArrayList<Double>> calculationOf_Expenses_Income(HashMap<Integer, ArrayList<MonthlyReport>> ReportResultM) {
        ArrayList<ArrayList<Double>> result_Expenses_Income = new ArrayList<>();
        ArrayList<Double> AmountOfExpenses = new ArrayList<>();
        ArrayList<Double> AmountOfIncome = new ArrayList<>();
        for (int i = 0; i < ReportResultM.size(); i++) {
            double AmountOfExpensesTemporary = 0;
            double AmountOfIncomeTemporary = 0;
            ArrayList<MonthlyReport> list = ReportResultM.get(i + 1);

            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).is_expense) {
                    AmountOfExpensesTemporary += list.get(j).quantity * list.get(j).sum_of_one;
                } else {
                    AmountOfIncomeTemporary += list.get(j).quantity * list.get(j).sum_of_one;
                }
            }
            AmountOfExpenses.add(AmountOfExpensesTemporary);
            AmountOfIncome.add(AmountOfIncomeTemporary);
        }
        result_Expenses_Income.add(AmountOfExpenses);
        result_Expenses_Income.add(AmountOfIncome);
        return result_Expenses_Income;
    }

    void reportComparison(HashMap<Integer, ArrayList<YearlyReport>> ReportResultY, ArrayList<ArrayList<Double>> result_Expenses_Income) {

        if (Validation(ReportResultY, result_Expenses_Income)) {
            for (int i = 0; i < ReportResultY.size(); i++) {
                ArrayList<YearlyReport> list = ReportResultY.get(yearR.getKey() + i);
                int exp = 0;
                int inc = 0;
                int error = 0;
                for (int j = 0; j < list.size(); j++) {

                    if (list.get(j).is_expense) {

                        if (list.get(j).amount != result_Expenses_Income.get(0).get(exp++)) {
                            System.out.println("Обнаружена ошибка в расходах " + exp + " месяца.");
                            error++;
                        }
                    } else {
                        if (list.get(j).amount != result_Expenses_Income.get(1).get(inc++)) {
                            System.out.println("Обнаружена ошибка в доходах " + inc + " месяца.");
                            error++;
                        }
                    }
                }
                if (error == 0) {
                    System.out.println("Сверка данных успешно проведена, несоответствий нет.");
                }
            }

        }
    }

    public static boolean Validation(HashMap<Integer, ArrayList<YearlyReport>> ReportResultY, ArrayList<ArrayList<Double>> result_Expenses_Income) {
        if (ReportResultY.size() < 1 || result_Expenses_Income.size() < 1) {
            System.out.println("Месячные или годовые отчеты не были считаны.");
            return false;
        } else {
            return true;
        }
    }

    public static boolean Validation(HashMap ReportResultY) {
        if (ReportResultY.size() < 1) {
            System.out.println("Месячные или годовые отчеты не были считаны.");
            return false;
        } else {
            return true;
        }
    }
}



