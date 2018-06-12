package com.ai.controller.web.v1;

import com.ai.domain.bo.App;
import com.ai.domain.bo.TaskUser;
import com.ai.domain.vo.Message;
import com.ai.service.AppService;
import com.ai.service.TaskUserService;
import com.ai.support.factory.LogTaskFactory;
import com.ai.support.manager.LogExeManager;
import com.ai.util.RandomUtil;
import com.ai.util.RequestResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @ResponseBody
    @PostMapping("/ex")
    public void excel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //用流的方式先读取到你想要的excel的文件
        FileInputStream fis=new FileInputStream(new File("D://ceshi.xls"));
        //解析excel
        POIFSFileSystem pSystem=new POIFSFileSystem(fis);
        //获取整个excel
        HSSFWorkbook hb=new HSSFWorkbook(pSystem);
        //System.out.println(hb.getNumCellStyles());
        //获取第一个表单sheet
        HSSFSheet sheet=hb.getSheetAt(0);
        //获取第一行
        int firstrow=    sheet.getFirstRowNum();
        //获取最后一行
        int lastrow=    sheet.getLastRowNum();
        //循环行数依次获取列数
        for (int i = firstrow; i < lastrow+1; i++) {
            //获取哪一行i
            Row row=sheet.getRow(i);
            if (row!=null) {
                //获取这一行的第一列
                int firstcell=    row.getFirstCellNum();
                //获取这一行的最后一列
                int lastcell=    row.getLastCellNum();
                //创建一个集合，用处将每一行的每一列数据都存入集合中
                List<String> list=new ArrayList<>();
                for (int j = firstcell; j <lastcell; j++) {
                    //获取第j列
                    Cell cell=row.getCell(j);

                    if (cell!=null) {
                        System.out.print(cell+"\t");
                        list.add(cell.toString());
                    }
                }

                TaskUser user=new TaskUser();
                if (list.size()>0) {
                    user.setTaskId(Long.parseLong("1"));
                    user.setMobile(list.get(1));
                }
                if(taskUserService.addTaskUser(user)){
                    System.out.println(1);
                }else{
                    System.out.println(2);
                }
            }
        }
        fis.close();
    }
}
