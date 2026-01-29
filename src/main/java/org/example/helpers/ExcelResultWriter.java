package org.example.helpers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.utils.ScenarioResult;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExcelResultWriter {
    private final List<ScenarioResult> results = new ArrayList<>();
    private CellStyle passStyle;
    private CellStyle failStyle;

    /* ==== API ==== */
    public void add(ScenarioResult r) { results.add(r); }

    public void writeReport() {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = "TestResult_" + timestamp + ".xlsx";
        Path outDir = Path.of("reports");
        try { if (Files.notExists(outDir)) Files.createDirectories(outDir); }
        catch (IOException e) { throw new RuntimeException(e); }

        try (Workbook wb = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(outDir.resolve(fileName).toFile())) {

            initStyles(wb);
            writeDetailSheet(wb);
            writeSummarySheet(wb);
            wb.write(fos);
            System.out.println("📊  Đã ghi báo cáo: " + outDir.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Không ghi được file Excel", e);
        }
    }

    /* ==== Sheet Detail ==== */
    private void writeDetailSheet(Workbook wb) {
        Sheet sheet = wb.createSheet("Detail");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Feature");
        header.createCell(1).setCellValue("Test Case");
        header.createCell(2).setCellValue("Status");
        header.createCell(3).setCellValue("Duration (ms)");

        int rowIdx = 1;
        for (ScenarioResult r : results) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(r.getFeature());
            row.createCell(1).setCellValue(r.getName());

            Cell statusCell = row.createCell(2);
            statusCell.setCellValue(r.getStatus());
            statusCell.setCellStyle("PASSED".equalsIgnoreCase(r.getStatus())
                    ? passStyle : failStyle);

            row.createCell(3).setCellValue(r.getDurationMs());
        }
        for (int i = 0; i < 4; i++) sheet.autoSizeColumn(i);
    }

    /* ==== Sheet Summary ==== */
    private void writeSummarySheet(Workbook wb) {
        long passed = results.stream()
                .filter(r -> "PASSED".equalsIgnoreCase(r.getStatus()))
                .count();
        long failed = results.size() - passed;

        Sheet sum = wb.createSheet("Summary");
        Row h = sum.createRow(0);
        h.createCell(0).setCellValue("Total");
        h.createCell(1).setCellValue("Passed");
        h.createCell(2).setCellValue("Failed");

        Row d = sum.createRow(1);
        d.createCell(0).setCellValue(results.size());
        d.createCell(1).setCellValue(passed);
        d.createCell(2).setCellValue(failed);

        for (int i = 0; i < 3; i++) sum.autoSizeColumn(i);
    }

    /* ==== Styles ==== */
    private void initStyles(Workbook wb) {
        passStyle = wb.createCellStyle();
        passStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        passStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        failStyle = wb.createCellStyle();
        failStyle.setFillForegroundColor(IndexedColors.ROSE.getIndex());
        failStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }
}
