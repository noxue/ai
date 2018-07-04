package com.ai.service.impl;

import com.ai.config.UploadConfig;
import com.ai.domain.bo.TaskUser;
import com.ai.service.ExcelService;
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
import sun.misc.BASE64Encoder;

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

    @Autowired
    UploadConfig uploadConfig;

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
        // 指定允许其他域名访问    // 响应类型
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","GET");
        // 响应头设置
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
        // 创建新的Excel 工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 设置字体
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 14);
        font.setFontName("宋体");
        // 设置样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 在Excel工作簿中建一工作表，其名为缺省值
        HSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(0, "营销统计");
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
        row=sheet.createRow((short)0);
        row.createCell((short) 0).setCellValue("客户姓名");
        row.createCell((short) 1).setCellValue("客户电话");
        row.createCell((short) 2).setCellValue("呼叫状态");
        row.createCell((short) 3).setCellValue("客户分类");
        row.createCell((short) 4).setCellValue("分类原因");
        row.createCell((short) 5).setCellValue("呼叫时间");
        row.createCell((short) 6).setCellValue("通话时间");
        row.createCell((short) 7).setCellValue("跟进状态");

        //填充数据
        for (int i = 0; i < rowList.size(); i++) {
            row=sheet.createRow(i+1);
            for (int j = 0; j < rowList.get(i).length; j++){
                row.createCell((short)j).setCellValue(rowList.get(i)[j]);
            }
        }
        String fileName = "asda";
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        workbook.write(os);
        byte[] content = os.toByteArray();
       // byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
      /*  response.setContentType("application/vnd.ms-excel;charset=GBK");
        response.addHeader("Content-Disposition", "attachment;filename="
                + new String((fileName + ".xls").getBytes(), "GBK"));*/
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        out.flush();
    }

}
