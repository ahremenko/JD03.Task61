package java.by.htp.ahremenko.task61.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.by.htp.ahremenko.task61.model.Book;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;


public class CsvHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "name", "create year", "genre", "author" };

    public static ByteArrayInputStream booksToCsv(Collection<Book> books) throws IOException {
        FileWriter fileWriter = new FileWriter("books.csv");
        try (CSVPrinter printer = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(HEADERs)); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            books.forEach( book -> { printer.printRecord(book.getName(), book.getCreateYear(), book.getAuthor().getName(), book.getGenre().getName());
            });
        }
        return new ByteArrayInputStream(fileWriter.toByteArray());
    }


    public static ByteArrayInputStream tutorialsToExcel(List<Tutorial> tutorials) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (Tutorial tutorial : tutorials) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(tutorial.getId());
                row.createCell(1).setCellValue(tutorial.getTitle());
                row.createCell(2).setCellValue(tutorial.getDescription());
                row.createCell(3).setCellValue(tutorial.isPublished());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }


}
