package com.jwt.token.sample.loginApp.service.impl;

import com.jwt.token.sample.loginApp.constant.FileContentType;
import com.jwt.token.sample.loginApp.domain.entity.User;
import com.jwt.token.sample.loginApp.domain.model.ReportExcel;
import com.jwt.token.sample.loginApp.dto.DownloadFileResponse;
import com.jwt.token.sample.loginApp.repository.UserRepository;
import com.jwt.token.sample.loginApp.service.ExcelFileAdapter;
import com.jwt.token.sample.loginApp.service.GenerateUserReport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenerateUserReportImpl implements GenerateUserReport {

    private final UserRepository userRepository;
    private final ExcelFileAdapter excelFileAdapter;

    private static final String SHEET_NAME = "User Report";
    private static final String ID_COLUMN = "Id";
    private static final String USERNAME_COLUMN = "Username";
    private static final String FILE_NAME = "User Report";

    @Override
    public DownloadFileResponse execute() {
        List<User> users = userRepository.findAll();
        ReportExcel<User> userReportExcel = buildUserReportExcel(users);
        byte[] excelData = excelFileAdapter.generateExcelFile(userReportExcel);

        DownloadFileResponse downloadFileResponse = new DownloadFileResponse();
        downloadFileResponse.setContentType(FileContentType.EXCEL.getContentType());
        downloadFileResponse.setFileName(FILE_NAME);
        downloadFileResponse.setFileByteArray(excelData);
        return downloadFileResponse;
    }

    private ReportExcel<User> buildUserReportExcel(List<User> users) {
        ReportExcel<User> reportExcel = new ReportExcel<>();
        reportExcel.setSheetName(SHEET_NAME);
        reportExcel.setData(users);
        reportExcel.setDataColumnMappers(buildColumnMapper());
        return reportExcel;
    }

    private Map<String, Function<User, ?>> buildColumnMapper() {
        Map<String, Function<User, ?>> columnMapper = new LinkedHashMap<>();
        columnMapper.put(ID_COLUMN, this::getId);
        columnMapper.put(USERNAME_COLUMN, this::getUser);
        return columnMapper;
    }

    private Long getId(User user) {
        return user.getId();
    }

    private String getUser(User user) {
        return user.getUsername();
    }
}
