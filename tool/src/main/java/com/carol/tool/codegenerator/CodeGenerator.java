package com.carol.tool.codegenerator;

import com.carol.tool.domain.Entity;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 代码生成工具
 */
public class CodeGenerator {

    ClasspathResourceLoader resourceLoader;
    Configuration cfg;
    GroupTemplate gt;
    private static  class CodeGeneratorHolder{
        static CodeGenerator instance = new CodeGenerator();
    }

    private CodeGenerator(){
        try{
              resourceLoader = new ClasspathResourceLoader();
              cfg = Configuration.defaultConfiguration();
              gt = new GroupTemplate(resourceLoader, cfg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static CodeGenerator getInstance(){
        return CodeGeneratorHolder.instance;
    }

    public void generateModel(String modelFile, Entity entity){
        Template t = gt.getTemplate("/model.btl");
        t.binding("entity",entity);
        System.out.println(t.render());
        try {
            Files.write(Paths.get(modelFile),t.render().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取Ctrl模板
     * @return
     */
    public Template getCtrlTemplate(){
        return gt.getTemplate("/ctrl.btl");
    }
    /**
     * 获取模板
     * @return
     */
    public Template getTemplate(String key){
        return gt.getTemplate(key);
    }
    /**
     * 获取Dao模板
     * @return
     */
    public Template getDaoTemplate(){
        return gt.getTemplate("/dao.btl");
    }
    /**
     * 获取Ctrl模板
     * @return
     */
    public Template getServiceTemplate(){
        return gt.getTemplate("/service.btl");
    }
    /**
     * 获取Page模板
     * @return
     */
    public Template getPageTemplate(){
        return gt.getTemplate("/pageEdit.btl");
    }
    /**
     * 获取JS模板
     * @return
     */
    public Template getJsTemplate(){
        return gt.getTemplate("/js.btl");
    }
}
