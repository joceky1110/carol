package com.carol.tool.codegenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.carol.tool.BeetlTool;
import com.carol.tool.domain.Entity;
import com.carol.tool.domain.Column;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * 从excel生成Model
 */
public class GenerateModel {

    private static String projectPath = "D:\\workspace\\bookcircle\\";
    private static String excelFile = projectPath+"tool\\src\\main\\resources\\design.xlsx";
    private static String modelPath = projectPath+"model\\src\\main\\java\\com\\carol\\model\\";
    /**
     * 根据Sheet生成model
     * @throws Exception
     */
    @Test
    public void generateModelByindex( ) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(excelFile)));
        XSSFSheet sheet = null;
        int i = 7;
            sheet = workbook.getSheetAt(i);
            String name =  sheet.getSheetName();
            String ns[] = name.split("-");
            Entity entity = new Entity();
            entity.setTitle(ns[0]);
            entity.setModelName(BeetlTool.normalizeEnity(ns[1]));
            entity.setTableName(ns[1].toUpperCase());
            List<Column> columnList = new ArrayList<Column>();
            entity.setColumns(columnList);
            for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {
                XSSFRow row = sheet.getRow(j);
                if (row != null) {
                    Column c = new Column();
                    c.setTitle (row.getCell(0).toString());
                    c.setColumn(BeetlTool.normalize(row.getCell(1).toString()));
                    c.setColumnSql(row.getCell(1).toString().toUpperCase());
                    c.setDataType(BeetlTool.getDataType(row.getCell(2).toString()));
                    c.setDefinition(row.getCell(2).toString());
                    c.setIsNotNull(row.getCell(3)!=null?row.getCell(3).toString():"否");
                    c.setColumnDesc(row.getCell(4)!=null?row.getCell(4).toString():"");
                    c.setColumnRemark(row.getCell(5)!=null?row.getCell(5).toString():"");
                    if(StringUtils.isNotEmpty(c.getTitle())){
                        columnList.add(c);
                    }
                }
            }
            CodeGenerator.getInstance().generateModel(modelPath+entity.getModelName()+".java",entity);
    }

    /**
     * 根据Sheet生成model
     * @throws Exception
     */
    @Test
    public void generateModel() throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(excelFile)));
        XSSFSheet sheet = null;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheet = workbook.getSheetAt(i);
            String name =  sheet.getSheetName();
            String ns[] = name.split("-");
            Entity entity = new Entity();
            entity.setTitle(ns[0]);
            entity.setModelName(BeetlTool.normalizeEnity(ns[1]));
            entity.setTableName(ns[1].toUpperCase());
            List<Column> columnList = new ArrayList<Column>();
            entity.setColumns(columnList);
            for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {
                XSSFRow row = sheet.getRow(j);
                if (row != null) {
                    Column c = new Column();
                    c.setTitle (row.getCell(0).toString());
                    c.setColumn(BeetlTool.normalize(row.getCell(1).toString()));
                    c.setColumnSql(row.getCell(1).toString().toUpperCase());
                    c.setDataType(BeetlTool.getDataType(row.getCell(2).toString()));
                    c.setDefinition(row.getCell(2).toString());
                    c.setIsNotNull(row.getCell(3)!=null?row.getCell(3).toString():"否");
                    c.setColumnDesc(row.getCell(4)!=null?row.getCell(4).toString():"");
                    c.setColumnRemark(row.getCell(5)!=null?row.getCell(5).toString():"");
                    if(StringUtils.isNotEmpty(c.getTitle())){
                        columnList.add(c);
                    }
                }
            }
            CodeGenerator.getInstance().generateModel(modelPath+entity.getModelName()+".java",entity);
        }
    }
 
    // 读取，指定sheet表及数据
    @Test
    public void showExcel2() throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("E:/temp/t1.xls")));
        HSSFSheet sheet = null;
        int i = workbook.getSheetIndex("xt"); // sheet表名
        sheet = workbook.getSheetAt(i);
        for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {// getLastRowNum
                                                                // 获取最后一行的行标
            HSSFRow row = sheet.getRow(j);
            if (row != null) {
                for (int k = 0; k < row.getLastCellNum(); k++) {// getLastCellNum
                                                                // 是获取最后一个不为空的列是第几个
                    if (row.getCell(k) != null) { // getCell 获取单元格数据
                        System.out.print(row.getCell(k) + "\t");
                    } else {
                        System.out.print("\t");
                    }
                }
            }
            System.out.println("");
        }
    }
 
    // 写入，往指定sheet表的单元格
    @Test
    public void insertExcel3() throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("E:/temp/t1.xls"))); // 读取的文件
        HSSFSheet sheet = null;
        int i = workbook.getSheetIndex("xt"); // sheet表名
        sheet = workbook.getSheetAt(i);
 
        HSSFRow row = sheet.getRow(0); // 获取指定的行对象，无数据则为空，需要创建
        if (row == null) {
            row = sheet.createRow(0); // 该行无数据，创建行对象
        }
 
        Cell cell = row.createCell(1); // 创建指定单元格对象。如本身有数据会替换掉
        cell.setCellValue("tt"); // 设置内容
 
        FileOutputStream fo = new FileOutputStream("E:/temp/t1.xls"); // 输出到文件
        workbook.write(fo);
 
    }


    public static  void main( String[] args){
        GenerateModel gm = new GenerateModel();
        try {
            gm.generateModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}