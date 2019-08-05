package com.future.tcfm.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ExcelReaderService {

    void store(MultipartFile multipartFile);
    ByteArrayInputStream loadFile() throws IOException;
}
