package com.jwt.token.sample.loginApp.service.impl;

import com.google.common.collect.ImmutableList;
import com.jwt.token.sample.loginApp.constant.FileContentType;
import com.jwt.token.sample.loginApp.domain.entity.User;
import com.jwt.token.sample.loginApp.domain.model.ReportExcel;
import com.jwt.token.sample.loginApp.dto.DownloadFileResponse;
import com.jwt.token.sample.loginApp.exception.ApiException;
import com.jwt.token.sample.loginApp.repository.UserRepository;
import com.jwt.token.sample.loginApp.service.GenerateUserReport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenerateUserReportImpl implements GenerateUserReport {

    private final UserRepository userRepository;

    private static final int DEFAULT_FONT_SIZE = 11;
    private static final String SHEET_NAME = "User Report";
    private static final String ID_COLUMN = "Id";
    private static final String USERNAME_COLUMN = "Username";
    private static final String FILE_NAME = "User Report";


    @Override
    public DownloadFileResponse execute() {
        List<User> users = userRepository.findAll();
        ReportExcel<User> userReportExcel = buildUserReportExcel(users);
        byte[] excelData = createExcelFile(userReportExcel);

        DownloadFileResponse downloadFileResponse = new DownloadFileResponse();
        downloadFileResponse.setContentType(FileContentType.EXCEL.getContentType());
        downloadFileResponse.setFileName(FILE_NAME);
        downloadFileResponse.setFileByteArray(excelData);
        return downloadFileResponse;
    }

    private ReportExcel<User> buildUserReportExcel(List<User> users){
        ReportExcel<User> reportExcel = new ReportExcel<>();
        reportExcel.setSheetName(SHEET_NAME);
        reportExcel.setData(users);
        reportExcel.setDataColumnMappers(buildColumnMapper());
        return reportExcel;
    }

    private Map<String, Function<User, ?>> buildColumnMapper(){
        Map<String, Function<User, ?>> columnMapper = new LinkedHashMap<>();
        columnMapper.put(ID_COLUMN, this::getId);
        columnMapper.put(USERNAME_COLUMN, this::getUser);
        return columnMapper;
    }

    private Long getId(User user){
        return user.getId();
    }

    private String getUser(User user){
        return user.getUsername();
    }


    private <T> byte[] createExcelFile(ReportExcel<T> excel) {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet(excel.getSheetName());
        List<String> fieldKeys = ImmutableList.copyOf(excel.getDataColumnMappers().keySet());
        createDetailsTableHeader(workbook, sheet, fieldKeys);
        writeDataToSheet(sheet, workbook, excel, fieldKeys);
        resizeColumn(sheet, fieldKeys.size());
        return createFileContent(workbook);
    }

    private byte[] createFileContent(Workbook workbook){
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            workbook.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            log.warn("Cannot Create an excel file", e);
            throw new ApiException("Cannot Create excel file");
        }
    }

    private void createDetailsTableHeader(Workbook workbook, Sheet sheet, List<String> fieldKeys) {
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) DEFAULT_FONT_SIZE);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);

        Row headerRow = sheet.createRow(sheet.getLastRowNum() + 1);
        for (int i = 0; i < fieldKeys.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(fieldKeys.get(i));
            cell.setCellStyle(cellStyle);
        }
    }

    private <T> void writeDataToSheet(
            Sheet sheet,
            Workbook workbook,
            ReportExcel<T> reportExcel,
            List<String> fieldKeys
    ) {
        int rowIndex = sheet.getLastRowNum() + 1;

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        for (T rowData : reportExcel.getData()) {
            Row row = sheet.createRow(rowIndex++);

            for (int i = 0; i < fieldKeys.size(); i++) {
                Cell cell = row.createCell(i);

                Function<T, ?> extractFunction = reportExcel.getDataColumnMappers().get(fieldKeys.get(i));
                Object value = extractFunction.apply(rowData);
                if (value instanceof Double) {
                    cell.setCellValue((double) value);
                } else if(value instanceof String) {
                    cell.setCellValue((String) value);
                }
                cell.setCellStyle(cellStyle);
            }
        }
    }

    private void resizeColumn(Sheet sheet, int count){
        for(int i=0; i< count; i++)
            sheet.autoSizeColumn(i);
    }
}
