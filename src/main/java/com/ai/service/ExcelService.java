package com.ai.service;


import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/* *
 * @Author ws
 * @Description 
 * @2018年6月13日9:39:21
 */
public interface ExcelService {

    void importExcel(MultipartFile file) throws IOException;

    void downloadExcel(List<String[]> rowList, HttpServletResponse response) throws IOException;

}
