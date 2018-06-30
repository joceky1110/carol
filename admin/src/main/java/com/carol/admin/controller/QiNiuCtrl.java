package com.carol.admin.controller;

import com.carol.admin.base.PageTable;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
public class QiNiuCtrl {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String accessKey = "U1wwWTgrw1qhy--z7PYmygLbbYNO9zJkqAOxJ5Zq";
    private final static String secretKey = "xtO5ENlbdPOuq-Q_nrhLFHPOveS5FoCDF3n7pUFx";
    private static String bucket = "xiwen";
    private static String domain = "http://pb2y688rw.bkt.clouddn.com/";

    @RequestMapping("/qiniu/getToken")
    @ResponseBody
    public String getToken(HttpServletRequest request) {

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }

    @ResponseBody
    @PostMapping("/qiniu/uploadImage.do")
    public Map<String, Object> uploadImage(@RequestParam(required = false) MultipartFile file) {
        try {
            Map<String, Object> succMap = new HashMap<>();
            String imgUrl = uploadFile(file.getInputStream());
            succMap.put("error", 0);
            succMap.put("url", imgUrl);
            return succMap;
        } catch (Exception e){
            Map<String, Object> msg = new HashMap<String, Object>();
            msg.put("error", 1);
            msg.put("message", "<font size='3'>您选择的文件上传失败！</font>");
            return msg;
        }
    }

    public String uploadFile(InputStream in) {
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        DefaultPutRet putRet = null;
        try {
            //这里不传key，云端用hash值作为文件名
            Response response = uploadManager.put(in, null, upToken, null, null);
            // 解析上传成功的结果
            putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            logger.debug("上传成功,key:{},hash:{}", putRet.key, putRet.hash);
        } catch (QiniuException ex) {
            ex.printStackTrace();
            Response r = ex.response;
            logger.debug("上传出错，{}" + r.toString());
        }
        return domain + putRet.key;
    }


public static void main(String arg[]) {
    //构造一个带指定Zone对象的配置类
    Configuration cfg = new Configuration(Zone.zone0());
//...其他参数参考类注释
    UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传

//如果是Windows情况下，格式是 D:\\qiniu\\test.png
    String localFilePath = "D:\\bak\\118一园青菜成了精\\1.jpg";
//默认不指定key的情况下，以文件内容的hash值作为文件名
    String key = null;
    Auth auth = Auth.create(accessKey, secretKey);
    String upToken = auth.uploadToken(bucket);
    try {
        Response response = uploadManager.put(localFilePath, key, upToken);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        System.out.println(putRet.key);
        System.out.println(putRet.hash);
    } catch (QiniuException ex) {
        Response r = ex.response;
        System.err.println(r.toString());
        try {
            System.err.println(r.bodyString());
        } catch (QiniuException ex2) {
            //ignore
        }
    }
}
}