package com.carol.tool.codegenerator;

import com.carol.tool.BeetlTool;
import com.carol.tool.domain.Column;
import com.carol.tool.domain.Entity;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.beetl.core.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 从excel生成Model
 */
public class GenerateCode {

    private static String projectPath = "D:\\workspace\\bookcircle\\";
    private static String excelFile = projectPath+"tool\\src\\main\\resources\\design.xlsx";
    private static String modelPath = projectPath+"model\\src\\main\\java\\com\\carol\\model\\dorcst\\";
    /**
     * 代码根目录
     */
    private static String srcPath = projectPath+"admin\\src\\main\\java\\com\\carol\\admin\\";
    /**
     * 页面根目录
     */
    private static String pagePath = projectPath+"admin\\src\\main\\resources\\templates\\page\\";
    /**
     * js根目录
     */
    private static String jsPath= projectPath+"admin\\src\\main\\resources\\static\\js\\";

    public static void main(String[] args){
        GenerateCode gc = new GenerateCode();
        try {
            gc.generatCode(6);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 指定SHEET生成代码
     * @throws Exception
     */
    @Test
    public void generatCode(int i) throws Exception {
        Entity entity =  getEntity(i);
        this.generateDao(entity);
        this.generateService(entity);
        this.generateCtrl(entity);
        this.generatePage(entity);
        this.generateJs(entity);
    }

    /**
     * 根据Entity生成Dao
     * @throws Exception
     */
    @Test
    public void generateDao(Entity entity) throws Exception {
        Template t = CodeGenerator.getInstance().getDaoTemplate();
        t.binding("entity",entity);
        Files.write(Paths.get(srcPath+"dao\\"+entity.getModelName()+"Dao.java"),t.render().getBytes());
    }
    /**
     * 根据Entity生成Service
     * @throws Exception
     */
    @Test
    public void generateService(Entity entity) throws Exception {
        Template t = CodeGenerator.getInstance().getServiceTemplate();
        t.binding("entity",entity);
        t.binding("entityName",BeetlTool.lowerFirst(entity.getModelName()));
        Files.write(Paths.get(srcPath+"service\\"+entity.getModelName()+"Service.java"),t.render().getBytes());
    }


    /**
     * 根据Entity生成Ctrl
     * @throws Exception
     */
    @Test
    public void generateCtrl(Entity entity) throws Exception {
        Template t = CodeGenerator.getInstance().getCtrlTemplate();
        t.binding("entity",entity);
        t.binding("entityName",BeetlTool.lowerFirst(entity.getModelName()));
        Files.write(Paths.get(srcPath+"controller\\"+entity.getModelName()+"Ctrl.java"),t.render().getBytes());
    }
    /**
     * 根据Entity生成Page
     * @throws Exception
     */
    @Test
    public void generatePage(Entity entity) throws Exception {
        new File(pagePath+BeetlTool.lowerFirst(entity.getModelName())).mkdirs();
        Template t = CodeGenerator.getInstance().getTemplate("/pageIndex.btl");
        t.binding("entity",entity);
        t.binding("entityName",BeetlTool.lowerFirst(entity.getModelName()));
        Files.write(Paths.get(pagePath+BeetlTool.lowerFirst(entity.getModelName())+
                "\\index.html"),t.render().getBytes());

          t = CodeGenerator.getInstance().getTemplate("/pageEdit.btl");
        t.binding("entity",entity);
        t.binding("entityName",BeetlTool.lowerFirst(entity.getModelName()));
        Files.write(Paths.get(pagePath+BeetlTool.lowerFirst(entity.getModelName())+
                "\\edit.html"),t.render().getBytes());

        t = CodeGenerator.getInstance().getTemplate("/pageAdd.btl");
        t.binding("entity",entity);
        t.binding("entityName",BeetlTool.lowerFirst(entity.getModelName()));
        Files.write(Paths.get(pagePath+BeetlTool.lowerFirst(entity.getModelName())+
                "\\add.html"),t.render().getBytes());

        t = CodeGenerator.getInstance().getTemplate("/pageInfo.btl");
        t.binding("entity",entity);
        t.binding("entityName",BeetlTool.lowerFirst(entity.getModelName()));
        Files.write(Paths.get(pagePath+BeetlTool.lowerFirst(entity.getModelName())+
                "\\info.html"),t.render().getBytes());

    }
    /**
     * 根据Entity生成Js
     * @throws Exception
     */
    @Test
    public void generateJs(Entity entity) throws Exception {
        new File(jsPath+BeetlTool.lowerFirst(entity.getModelName())).mkdirs();
        Template t = CodeGenerator.getInstance().getTemplate("/jsIndex.btl");
        t.binding("entity",entity);
        t.binding("entityName",BeetlTool.lowerFirst(entity.getModelName()));
        Files.write(Paths.get(jsPath+BeetlTool.lowerFirst(entity.getModelName())+
                "\\index.js"),t.render().getBytes());

        t = CodeGenerator.getInstance().getTemplate("/jsEdit.btl");
        t.binding("entity",entity);
        t.binding("entityName",BeetlTool.lowerFirst(entity.getModelName()));
        Files.write(Paths.get(jsPath+BeetlTool.lowerFirst(entity.getModelName())+
                "\\edit.js"),t.render().getBytes());

        t = CodeGenerator.getInstance().getTemplate("/jsAdd.btl");
        t.binding("entity",entity);
        t.binding("entityName",BeetlTool.lowerFirst(entity.getModelName()));
        Files.write(Paths.get(jsPath+BeetlTool.lowerFirst(entity.getModelName())+
                "\\add.js"),t.render().getBytes());
    }

    /**
     * 根据Sheet获取Entity
     * @throws Exception
     */
    @Test
    public Entity getEntity(int i) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(excelFile)));
        XSSFSheet sheet = null;
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
                c.setIsSearch("是".equals(row.getCell(6)+"")?true:false);
                c.setIsList("是".equals(row.getCell(7)+"")?true:false);
                c.setFilterType(row.getCell(8)!=null?row.getCell(8).toString():"" );
                c.setInputType(row.getCell(9)!=null?row.getCell(9).toString():"" );
                c.setValidates(row.getCell(10)!=null?row.getCell(10).toString():"" );
                c.setShow("是".equals(row.getCell(11)+"")?false:true);
                if(StringUtils.isNotEmpty(c.getTitle())){
                    columnList.add(c);
                }
            }
        }
        //CodeGenerator.getInstance().generateModel(modelPath+entity.getModelName()+".java",entity);
        return entity;
    }
}