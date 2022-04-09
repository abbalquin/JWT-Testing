package com.jwt.token.sample.loginApp.service;

import com.jwt.token.sample.loginApp.domain.entity.User;
import com.jwt.token.sample.loginApp.domain.model.ReportExcel;

public interface ExcelFileAdapter {

    byte[] generateExcelFile(ReportExcel<User> reportExcel);
}
