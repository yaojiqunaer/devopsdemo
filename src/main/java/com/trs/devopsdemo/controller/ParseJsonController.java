package com.trs.devopsdemo.controller;

import cn.hutool.core.io.resource.ResourceUtil;
import com.alibaba.fastjson.JSON;
import com.trs.devopsdemo.entity.JsonBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName ParseJson
 * @Description:
 * @Author zhangxiaodong
 * @Date 2020/7/7 15:05
 * @Version V1.0
 */
@RestController
@RequestMapping("/api")
public class ParseJsonController {


    private static  final Logger logger = LoggerFactory.getLogger(ParseJsonController.class);

    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String UPLOAD_PATH_PREFIX = "static/uploadFile/";

    @RequestMapping("/parse")
    public static JsonBean parseJson(@RequestParam MultipartFile file, HttpServletRequest request){//上传的api.json文件
        // 文件为空响应为：上传文件为空
        if (file.isEmpty()) {
            return new JsonBean(-999,"请上传文件",null);
        }
        String originalFilename = file.getOriginalFilename();//原始文件名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        //构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
        String realPath = new String("src/main/resources/" + UPLOAD_PATH_PREFIX);
        logger.info("-----------上传文件保存的路径【"+ realPath +"】-----------");
        String format = sdf.format(new Date());
        //存放上传文件的文件夹
        File upFile = new File(realPath + format);
        logger.info("-----------存放上传文件的文件夹【"+ upFile +"】-----------");
        logger.info("-----------输出文件夹绝对路径 -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径【"+ upFile.getAbsolutePath() +"】-----------");
        if(!upFile.isDirectory()){
            //递归生成文件夹
            upFile.mkdirs();
        }
        logger.info("-----------文件原始的名字【"+ originalFilename +"】-----------");
        String newName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
        logger.info("-----------文件要保存后的新名字【"+ newName +"】-----------");
        String filePath =null;
        File newFile=null;
        try {
            //构建真实的文件路径
         newFile = new File(upFile.getAbsolutePath() + File.separator + newName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            file.transferTo(newFile);
            filePath=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploadFile/" + format + newName;
            logger.info("-----------【"+ filePath +"】-----------");

        } catch (Exception e) {
            e.printStackTrace();
            return new JsonBean(-888,"文件上传失败！",null);
        }
        //UPLOAD_PATH_PREFIX+format+newName
       // return new JsonBean(0,"OK", ResourceUtil.readUtf8Str("file:"+filePath));

        return new JsonBean(0,"OK", JSON.parse(ResourceUtil.readUtf8Str("file:"+upFile.getAbsolutePath() + File.separator + newName)));
//        String json= FileUtil.readJsonFile(upFile.getAbsolutePath() + File.separator + newName);
//        try {
//            String content= FileUtils.readFileToString(newFile,"UTF-8");
//            //System.out.println(content);
//
//            //Map map = (Map) JSON.parse(content);
//
//
//
//            return new JsonBean(0,"OK", JSON.parse(content));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        logger.info("-----------Json解析结果【"+ json +"】-----------");
//        return new JsonBean(0,"OK",json);

    }



}
