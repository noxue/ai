package com.ai.service.impl;

import com.ai.domain.bo.TaskUser;
import com.ai.service.ExcelService;
import com.ai.service.TaskUserService;
import com.ai.support.factory.LogTaskFactory;
import com.ai.support.manager.LogExeManager;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("ExcelService")
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private TaskUserService taskUserService;

    public static String outputFile = "c:\\营销结果.xls";

    @Override
    public void importExcel(MultipartFile file) throws IOException {
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
        int firstrow=    sheet.getFirstRowNum();
        //获取最后一行
        int lastrow=    sheet.getLastRowNum();
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
                Cell cell1=row.getCell(1);
                name.add( cell1 == null ? space : cell1.toString());
                Cell cell2=row.getCell(2);
                phone.add( cell2 == null ? space: cell2.toString());
                Cell cell3=row.getCell(3);
                remark.add( cell3== null ? space : cell3.toString());

                // }
            }
        }
        List<TaskUser> list = new ArrayList<>();
        for (int j=1;j<phone.size();j++){
            TaskUser user=new TaskUser();
            user.setTaskId(Long.parseLong("1"));
            user.setMobile(phone.get(j));
            user.setName(name.get(j));
            user.setRemark(remark.get(j));
            user.setCalledAt(new Date());
            list.add(user);
        }

        if(taskUserService.insertTaskUserList(list)){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/excel/imp", "ImportExcel", (short) 6000, "导入成功"));
        }else{
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/excel/imp", "ImportExcel", (short) 6001, "导入失败"));
        }
        fis.close();
    }

    @Override
    public void downloadExcel(List<String[]> rowList, HttpServletResponse response) throws IOException {
        // 创建新的Excel 工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 设置字体
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 14);
        // 设置样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 在Excel工作簿中建一工作表，其名为缺省值
        HSSFSheet sheet = workbook.createSheet("营销统计");
        /*CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0,
                11);
        sheet.addMergedRegion(cellRangeAddress);*/

        //第一行
        // 在索引0的位置创建行（最顶端的行）
        HSSFRow row = sheet.createRow(0);
        // 在索引0的位置创建单元格（左上端）
        HSSFCell cell = row.createCell(0);
        // 定义单元格为字符串类型
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(cellStyle);
        row=sheet.createRow(0);
        row.createCell(0).setCellValue("客户姓名");
        row.createCell(1).setCellValue("客户电话");
        row.createCell(2).setCellValue("呼叫状态");
        row.createCell(3).setCellValue("客户分类");
        row.createCell(4).setCellValue("分类原因");
        row.createCell(5).setCellValue("呼叫时间");
        row.createCell(6).setCellValue("通话时间");
        row.createCell(7).setCellValue("跟进状态");

        //填充数据
        for (int i = 0; i < rowList.size(); i++) {
            row=sheet.createRow(i+1);
            for (int j = 0; j < rowList.get(i).length; j++){
                row.createCell(j).setCellValue(rowList.get(i)[j]);
            }
        }

        // 新建一输出文件流
        FileOutputStream fOut = new FileOutputStream(outputFile);
        // 把相应的Excel 工作簿存盘
        workbook.write(fOut);





        fOut.flush();
        // 操作结束，关闭文件
        fOut.close();
        System.out.println("文件生成...");

    }
}
