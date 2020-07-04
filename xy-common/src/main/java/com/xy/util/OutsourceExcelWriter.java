package com.xy.util;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * Created by zhaoyoulei on 2017/6/26.
 */
public class OutsourceExcelWriter {

    private String DATE_PATTERN = "yyyy-MM-dd";

    public void excelWriter(String title,String sheetName, List<Object> lists, String[] str , HttpServletRequest request , HttpServletResponse response,int subProperty) throws Exception{
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wkb = new HSSFWorkbook();
        HSSFCellStyle cellStyle=wkb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);

        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wkb.createSheet(sheetName);
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow rowTitle=sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=rowTitle.createCell(0);
        //设置单元格内容
        //cell.setCellValue(title);
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        //sheet.addMergedRegion(new CellRangeAddress(0,0,0,str.length-1));
        //在sheet里创建第二行
        //HSSFRow rowTop=sheet.createRow(1);

        cell.setCellStyle(cellStyle);
        //创建单元格并设置单元格内容
        for(int i=0 ; i<str.length ; i++ ){
            rowTitle.createCell(i, HSSFCell.CELL_TYPE_STRING).setCellValue(str[i]);
        }
        int rowNum = 0;
        for(int j = 0 ; j< lists.size() ;j ++) {

            List<Object> list = (List<Object>) lists.get(j);

            if (list.size() > 1) {
                sheet.addMergedRegion(new CellRangeAddress(rowNum+1, rowNum + list.size(), 0, 0));
            }
            Iterator it = list.iterator();
            Object t = null;
            String fieldName = null;
            Field field = null;
            String getMethodName = null;
            Class tCls = null;
            Method getMethod = null;
            Object value = null;
            while (it.hasNext()) {
                rowNum++;
                HSSFRow row = sheet.createRow(rowNum);

                t = it.next();
                Field[] fields = t.getClass().getDeclaredFields();

                for (short i = 0; i < fields.length-subProperty; ++i) {
                    cell = row.createCell(i, HSSFCell.CELL_TYPE_STRING);
                    field = fields[i];
                    fieldName = field.getName();
                    getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                    try {
                        tCls = t.getClass();
                        getMethod = tCls.getMethod(getMethodName, new Class[0]);
                        value = getMethod.invoke(t, new Object[0]);
                        setCellData(row, rowNum, i, value, cell, sheet);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("——————————————————创建Excel数据列表时出错。method:setDataRow,message：" + e.getMessage());
                    }
                }
            }
        }

        //输出Excel文件
        OutputStream output=response.getOutputStream();
        String userAgent = request.getHeader("USER-AGENT");
        title = StringUtil.getFileName(userAgent,title);
        response.reset();
        response.setContentType("application/msexcel; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + title +".xls");
        response.setCharacterEncoding("UTF-8");
        wkb.write(output);
        output.close();
    }


    private void setCellData(Row row, int index, int i, Object value, Cell cell, Sheet sheet) {
        String textValue = null;
        if(value instanceof Date) {
            Date p = (Date)value;
            SimpleDateFormat matcher = new SimpleDateFormat(DATE_PATTERN);
            textValue = matcher.format(p);
        }
        else if(value != null) {
            textValue = String.valueOf(value);
        } else {
            textValue = "";
        }

        if(textValue != null) {
            Pattern p2 = compile("^//d+(//.//d+)?$");
            Matcher matcher2 = p2.matcher(textValue);
            row.setHeightInPoints(20.0F);
            if(matcher2.matches()) {
                cell.setCellValue(Double.parseDouble(textValue));
            } else {
                cell.setCellValue(textValue);
            }
        }

    }

}
