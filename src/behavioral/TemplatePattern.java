package behavioral;

public class TemplatePattern {
    static abstract class Report{
        public final void generate(){
            fetchData();
            process();
            export();
        }
        protected void fetchData(){
            System.out.println("Fetching data");
        }
        protected abstract void process();
        protected abstract void export();
    }
    static class ExcelReport extends Report{

        @Override
        protected void process() {
            System.out.println("Excel Report Processing");
        }

        @Override
        protected void export() {
            System.out.println("Excel Report Exporting");
        }
    }

    static class PdfReport extends Report{
        @Override
        protected void process() {
            System.out.println("Pdf Report Processing");
        }

        @Override
        protected void export() {
            System.out.println("Pdf Report Exporting");
        }
    }

    public static void main(String[] args) {
        ExcelReport excelReport=new ExcelReport();
        excelReport.generate();

        PdfReport pdfReport=new PdfReport();
        pdfReport.generate();
    }
}
