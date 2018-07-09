package com.ai.service.impl;

import com.ai.config.UploadConfig;
import com.ai.domain.bo.Task;
import com.ai.domain.bo.TaskUser;
import com.ai.domain.vo.Message;
import com.ai.service.ExcelService;
import com.ai.service.TaskService;
import com.ai.service.TaskUserService;
import com.ai.support.factory.LogTaskFactory;
import com.ai.support.manager.LogExeManager;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("ExcelService")
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private TaskUserService taskUserService;

    @Autowired
    private TaskService taskService;

    @Autowired
    UploadConfig uploadConfig;

    @Override
    public Message importExcel(int id,MultipartFile file) throws IOException {
        FileInputStream fis = (FileInputStream)file.getInputStream();
        //用流的方式先读取到你想要的excel的文件
        //FileInputStream fis=new FileInputStream(new File("D://phoneTaskUser.xls"));
        //解析excel
        POIFSFileSystem pSystem=new POIFSFileSystem(fis);
        //获取整个excel
        HSSFWorkbook hb=new HSSFWorkbook(pSystem);
        //获取第一个表单sheet
        HSSFSheet sheet=hb.getSheetAt(0);
        //获取第一行
        int firstrow = sheet.getFirstRowNum();
        //获取最后一行
        int lastrow =  sheet.getLastRowNum();
        String space ="";
        List<String> phone=new ArrayList<>();
        List<String> name= new ArrayList<>();
        List<String> remark=new ArrayList<>();
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
                //for (int j = firstcell; j <lastcell; j++) {
                //获取第j列

                Cell cell0=row.getCell(0);
                cell0.setCellType(cell0.CELL_TYPE_STRING);
                phone.add( cell0 == null ? space : cell0.toString());
                Cell cell1=row.getCell(1);
                name.add( cell1 == null ? space: cell1.toString());
                Cell cell2=row.getCell(2);
                remark.add( cell2== null ? space : cell2.toString());
            }
        }
        List<TaskUser> list = new ArrayList<>();
        for (int j=1;j<phone.size();j++){
            TaskUser user=new TaskUser();
            user.setTaskId(Long.parseLong("1"));
            String flag = isPhone(phone.get(j));
            if(!flag.equals("OK")){
                return new Message().error(-1,flag);
            }
            user.setMobile(phone.get(j));
            user.setName(name.get(j));
            user.setRemark(remark.get(j));
            user.setCalledAt(new Date());
            user.setTaskId((long) id);
            list.add(user);
        }
        fis.close();
        if(taskUserService.insertTaskUserList(list)){
            Task task = taskService.getTaskById(id);
            int total =task.getTotal()+list.size();
            task.setTotal(total);
            if( taskService.editTask(task)){
                LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/excel/editTask", "ImportExcel", (short) 6000, "导入成功"));
                return new Message().ok(1,"操作成功");
            }else{
                LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/excel/editTask", "ImportExcel", (short) 6001, "导入失败,更新task错误"));
                return new Message().error(1,"操作失败");
            }
        }else{
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/excel/imp", "ImportExcel", (short) 6001, "导入失败"));
            return new Message().error(1,"操作失败");
        }
    }

    @Override
    public List<String[]> downloadExcel(TaskUser[] result)  {
        List<String[]> listArray = new ArrayList<String[]>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (result != null) {
            for (int i = 0; i < result.length; i++) {
                String[] arrays = new String[8];
                arrays[0] = result[i].getName();
                arrays[1] = result[i].getMobile().toString();
                if (result[i].getStatus().toString().equals("0")) {
                    arrays[2] = "通话完毕";
                } else if (result[i].getStatus().toString().equals("1")) {
                    arrays[2] = "任务未执行";
                } else if (result[i].getStatus().toString().equals("2")) {
                    arrays[2] = "任务被客户端获取";
                }
                arrays[3] = result[i].getType().toString();
                arrays[4] = result[i].getRemark().toString();
                if (result[i].getCalledAt() != null) {
                    arrays[5] = sdf.format(result[i].getCalledAt());
                } else {
                    arrays[5] = "";
                }
                arrays[6] = result[i].getTime().toString();
                if (result[i].getShare()) {
                    arrays[7] = "公开";
                } else {
                    arrays[7] = "未公开";
                }
                listArray.add(arrays);
            }
        }
        return listArray;
    }

    public String isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return "手机号应为11位数";
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {
                return "请填入正确的手机号";
            }
            return "OK";
        }
    }
}
