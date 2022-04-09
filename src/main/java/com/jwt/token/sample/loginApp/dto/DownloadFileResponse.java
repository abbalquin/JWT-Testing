package com.jwt.token.sample.loginApp.dto;

import com.jwt.token.sample.loginApp.constant.FileContentType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DownloadFileResponse {

    private String fileName;

    private String contentType;

    private byte[] fileByteArray;
}
