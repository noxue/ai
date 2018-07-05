package com.ai.service;


import com.ai.domain.bo.TaskUser;
import com.ai.domain.vo.Message;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/* *
 * @Author ws
 * @Description 
 * @2018年6月13日9:39:21
 */
public interface ExcelService {

    Message importExcel(MultipartFile file) throws IOException;

    List<String[]> downloadExcel(TaskUser[] rowList) throws IOException;

}
