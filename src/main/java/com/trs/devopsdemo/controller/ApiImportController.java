package com.trs.devopsdemo.controller;

import com.trs.devopsdemo.entity.JsonBean;
import com.trs.devopsdemo.service.InterfaceService;
import com.trs.devopsdemo.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * @ClassName YapiController
 * @Description: 接口数据导入
 * @Author zhangxiaodong
 * @Date 2020/7/8 14:36
 * @Version V1.0
 */

@Api("API管理")
@RestController
public class ApiImportController {

    private static final Logger logger = LoggerFactory.getLogger(ApiImportController.class);

    @Autowired
    private InterfaceService interfaceService;

    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    private static final String UPLOAD_PATH_PREFIX = "static/uploadFile/";


    /**
     * @param
     * @return
     * @description 文件导出
     */
    @ApiOperation("导出api的json文件")
    @GetMapping("export")
    public ResponseEntity<byte[]> ApiExport() {
        byte[] body = null;
        String jsonFile = FileUtil.readJsonFile("C:\\Program Files (x86)\\IdeaProjects\\devopsdemo\\src\\main" +
                "\\resources" +
                "\\json\\api.json");
        try {
            body = jsonFile.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("json/api.json");
//        try {
//            body = new byte[in.available()];
//            // in.read(body);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=api.json");
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> response = new ResponseEntity<>(body, headers, statusCode);
        return response;
    }


    /**
     * @param type 导入类型(yapi,swagger,postman)
     * @param file json文件
     * @param mode 合并方式(normal、smart)
     * @return
     * @description api.json文件导入
     */
    @ApiOperation("导出API的json文件")
    @PostMapping("/import")
    public JsonBean ApiImport(@RequestParam String type, @RequestParam String mode, @RequestParam MultipartFile file,
                              HttpServletRequest request) {
        if (!file.isEmpty()) {

            //byte[] bytes;
            try {
                //bytes = file.getBytes();

                // Create the file on server
                File serverFile = new File("D://"+file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                int length=0;
                byte[] buffer = new byte[1024];
                InputStream inputStream = file.getInputStream();
                while ((length = inputStream.read(buffer)) != -1) {
                    stream.write(buffer, 0, length);
                }
                //stream.write(bytes);
                stream.flush();
                stream.close();

                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());

                return null;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

        }else{
            System.out.println("文件内容为空");
        }
        return null;


//
//        if (Objects.isNull(type) || StringUtils.isEmpty(type)) {
//            return new JsonBean(-999, "未指定数据导入类型", null);
//        }
//        if (file.isEmpty()) {
//            return new JsonBean(-888, "请上传文件", null);
//        }
//        JSON apiJson = null;//文件中的json数据
//        try {
//            apiJson = (JSON) JSON.parse(FileUtil.readFromStream(file.getInputStream()));
//            //return new JsonBean(0,"OK",apiJson);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return interfaceService.saveApis(apiJson, type, mode);
    }




}
