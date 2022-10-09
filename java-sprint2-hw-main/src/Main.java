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

        label:
        while (true) {
            System.out.println("Введите команду:" + "\n" +
                    "1 - Считать все месячные отчёты;" + "\n" +
                    "2 - Считать годовой отчёт;" + "\n" +
                    "3 - Сверить отчёты;" + "\n" +
                    "4 - Вывести информацию о всех месячных отчётах;" + "\n" +
                    "5 - Вывести информацию о годовом отчёте;" + "\n" +
                    "exit - Выход.");
            String inPut = scanner.nextLine().trim();
            switch (inPut) {
                case COUNT_ALL_MONTHLY_REPORTS:
                    ReportReadingResultMonth = monthlyReport.readFileContentsOrNull();
                    System.out.println("Месячные отчеты успешно считаны.");
                    break;
                case CALCULATE_ANNUAL_REPORT:
                    ReportReadingResultYears = yearlyReport.readFileContentsOrNull();
                    System.out.println("Годовой отчет успешно считан.");
                    break;
                case VERIFY_REPORTS:
                    dataReconciliation.reportComparison(ReportReadingResultYears, dataReconciliation.calculationOf_Expenses_Income(ReportReadingResultMonth));
                    break;
                case DISPLAY_INFO_ABOUT_ALL_MONTHLY_REPORTS:
                    inf.monthReportInformation(ReportReadingResultMonth);
                    break;
                case OUTPUT_INFORMATION_ABOUT_ANNUAL_REPORT:
                    inf.yearReportInformation(ReportReadingResultYears);
                    break;
                case END_PROGRAM:
                    break label;
                default:
                    System.out.println("Команда отсутстует, повторите попытку.");
                    break;
            }
        }
    }
}

