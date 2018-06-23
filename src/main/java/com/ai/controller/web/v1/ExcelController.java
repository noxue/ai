package com.ai.controller.web.v1;

import com.ai.domain.bo.TaskUser;
import com.ai.service.ExcelService;
import com.ai.service.TaskUserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/* *
 * @Author ws
 * @Description add新增,get读取,put完整更新,patch部分更新,del删除
 * @2018年6月4日9:55:36
 */
@RestController
@RequestMapping("/web/api/v1/excel")
public class ExcelController extends BasicAction{

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    private TaskUserService taskUserService;

    @Autowired
    private ExcelService excelService;


    @ApiOperation(value = "导入excel", notes = "根据规定的模板导入客户信息")
    @ResponseBody
    @PostMapping("/imp")
    public void ImportExcel(@RequestParam("file") MultipartFile file) throws Exception {
        if(file ==null){
            return;
        }
        excelService.importExcel(file);
    }

    @ApiOperation(value = "导出excel", notes = "根据当前的用户信息按照规定的模板导出execl信息")
    @ResponseBody
    @PostMapping("/exp")
    public void ExportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String appId =  request.getHeader("appId");
        if(appId ==null || appId.equals("")){
            return;
        }
        TaskUser[] result = taskUserService.taskUserList(appId);
        List<String[]> listArray = new ArrayList<String[]>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if( result != null ) {
            for (int i = 0; i < result.length; i++) {
                String[] arrays = new String[8];
                arrays[0] = result[i].getName().toString();
                arrays[1] = result[i].getMobile().toString();
                if(result[i].getStatus().toString().equals("0")){
                    arrays[2] = "通话完毕";
                }else if(result[i].getStatus().toString().equals("1")){
                    arrays[2] = "任务未执行";
                }else if(result[i].getStatus().toString().equals("2")){
                    arrays[2] = "任务被客户端获取";
                }
                arrays[3] = result[i].getType().toString();
                arrays[4] = result[i].getRemark().toString();
                if(result[i].getCalledAt()!=null){
                    arrays[5]=sdf.format(result[i].getCalledAt());
                }else{
                    arrays[5]="";
                }
                arrays[6] = result[i].getTime().toString();
                    if (result[i].getShare()) {
                        arrays[7] ="公开";
                    }else{
                        arrays[7] ="未公开";
                    }
                listArray.add(arrays);
                }
            }
            excelService.downloadExcel(listArray,response);

        }

    }