import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static final String COUNT_ALL_MONTHLY_REPORTS = "1";
    private static final String CALCULATE_ANNUAL_REPORT = "2";
    private static final String VERIFY_REPORTS = "3";
    private static final String DISPLAY_INFO_ABOUT_ALL_MONTHLY_REPORTS = "4";
    private static final String OUTPUT_INFORMATION_ABOUT_ANNUAL_REPORT = "5";
    private static final String END_PROGRAM = "exit";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        DataReconciliation dataReconciliation = new DataReconciliation();
        ReportInformation inf = new ReportInformation();

        HashMap<Integer, ArrayList<YearlyReport>> ReportReadingResultYears = new HashMap<>();
        HashMap<Integer, ArrayList<MonthlyReport>> ReportReadingResultMonth = new HashMap<>();

        while (true) {
            System.out.println("Введите команду:" + "\n1 - Считать все месячные отчёты;" + "\n2 - Считать годовой отчёт;" + "\n3 - Сверить отчёты;" + "\n4 - Вывести информацию о всех месячных отчётах;" + "\n5 - Вывести информацию о годовом отчёте;" + "\nexit - Выход.");
            String inPut = scanner.nextLine().trim();
            if (COUNT_ALL_MONTHLY_REPORTS.equals(inPut)) {
                ReportReadingResultMonth = monthlyReport.readFileContentsOrNull();
                System.out.println("Месячные отчеты успешно считаны.");
            } else if (CALCULATE_ANNUAL_REPORT.equals(inPut)) {
                //"Введите год и добавить цикл для проверки по годам"
                ReportReadingResultYears = yearlyReport.readFileContentsOrNull();
                System.out.println("Годовой отчет успешно считан.");
            } else if (VERIFY_REPORTS.equals(inPut)) {
                dataReconciliation.reportComparison(ReportReadingResultYears, dataReconciliation.calculationOf_Expenses_Income(ReportReadingResultMonth));
            } else if (DISPLAY_INFO_ABOUT_ALL_MONTHLY_REPORTS.equals(inPut)) {
                inf.monthReportInformation(ReportReadingResultMonth);
            } else if (OUTPUT_INFORMATION_ABOUT_ANNUAL_REPORT.equals(inPut)) {
                inf.yearReportInformation(ReportReadingResultYears);
            } else if (END_PROGRAM.equals(inPut)) {
                break;
            } else {
                System.out.println("Команда отсутстует, повторите попытку.");
            }
        }
    }
}

