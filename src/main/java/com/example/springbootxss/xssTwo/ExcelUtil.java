package com.example.springbootxss.xssTwo;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

/**
 * todo
 */
public class ExcelUtil {

    /**
     * todo   上传文件时，过滤excel文件类型中的xss攻击
     */
    public static void readExcel(HttpServletRequest req){
       // InputStream is = null;
        HSSFWorkbook workbook = null;

        // 1.创建DiskFileItemFactory对象配置缓存信息
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 2.创建一个ServletFileUpload对象
        ServletFileUpload sfu = new ServletFileUpload(factory);
        // 3.设置文件名称编码格式
        sfu.setHeaderEncoding("utf-8");
        // 4.开始解析文件
        try {
            List<FileItem> items = sfu.parseRequest(req);
        // 5.获取文件信息
            for (FileItem item : items) {
                String fileName = item.getName();
                if(fileName==null||fileName.trim().equals("")){
                    continue;
                }
                    fileName = fileName.substring(fileName.lastIndexOf('/'),1);
                    if(fileName.endsWith(".xls")||fileName.endsWith(".xlsx")){
                        //获取item中的上传文件的输入流
                        InputStream is = item.getInputStream();

                        //根据后缀，得到不同的Workbook子类，即HSSFWorkbook或XSSFWorkbook
                        if(fileName.endsWith(".xls")){
                            workbook = new HSSFWorkbook(is);
                        }else {
                            //workbook = new XSSFWorkbook(is);
                        }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HSSFSheet sheet = workbook.getSheetAt(0);
//
//        Sheet[] sheetNum = workbook.getSheets();
//        System.out.println("打印sheet的個數："+sheetNum.length);
//        Sheet sheet = workbook.getSheet(0);
//        Cell cell = null;
//
//        int columnCount = sheet.getColumns();
//        int rowCount = sheet.getRows();
//        for (int i = 0; i < rowCount; i++) {
//            for (int j = 0; j < columnCount; j++) {
//                cell = sheet.getCell(j, i);
//                System.out.print(cell.getContents());
//            }
//            System.out.println(" \n");
//        }
//        resp.sendRedirect("/importexcel/index.jsp");
//        workbook.close();

    }
}
