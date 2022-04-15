package com.jwt.token.sample.loginApp.controller;

import com.jwt.token.sample.loginApp.dto.DownloadFileResponse;
import com.jwt.token.sample.loginApp.exception.ApiException;
import com.jwt.token.sample.loginApp.service.GenerateUserReport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileDownloadController {

    private final GenerateUserReport generateUserReport;

    private static final String CACHE_CONTROL_VALUE = "must-revalidate, post-check=0, pre-check=0";

    @GetMapping("v1/download/user")
    public ResponseEntity<byte[]> downloadUser() {
        DownloadFileResponse downloadFileResponse = generateUserReport.execute();
        return buildDownloadResource(downloadFileResponse);
    }

    private ResponseEntity<byte[]> buildDownloadResource(DownloadFileResponse downloadFileResponse) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(downloadFileResponse.getContentType()));
        headers.setContentDispositionFormData(downloadFileResponse.getFileName(), downloadFileResponse.getFileName());
        headers.setCacheControl(CACHE_CONTROL_VALUE);
        headers.setContentLength(downloadFileResponse.getFileByteArray().length);

        return new ResponseEntity<>(downloadFileResponse.getFileByteArray(), headers, HttpStatus.OK);
    }
}
