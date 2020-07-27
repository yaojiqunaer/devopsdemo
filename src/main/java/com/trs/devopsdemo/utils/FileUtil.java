package com.trs.devopsdemo.utils;

import java.io.*;

/**
 * @ClassName JsonFileUtil
 * @Description:
 * @Author zhangxiaodong
 * @Date 2020/7/7 15:21
 * @Version V1.0
 */
public class FileUtil {

//    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("张三");
//        list.add("李四");
//        list.add("王五");
//        list.add("赵六");
//        list.add("孙七");
//        list.forEach(user -> {
//            if ("李四".equals(user)) System.out.println(user);
//        });
//        list.stream().map(item -> "666" + item).collect(Collectors.toList()).stream().forEach(System.out::println);
//        list.stream().filter(str -> {
//            System.out.println(str);
//            return true;
//        }).collect(Collectors.toList());
//    }


    /**
     * @param is
     * @return
     * @description 流转为String
     */
    public static String readFromStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        is.read();
        String result = baos.toString();
        baos.close();
        return result;
    }

    //读取json文件
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
