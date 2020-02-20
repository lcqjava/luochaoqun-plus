package com.luochaoqun.plus.ideas.xiaoyuandiancan.web;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.luochaoqun.plus.ideas.xiaoyuandiancan.user.entity.User;
import com.luochaoqun.plus.ideas.xiaoyuandiancan.util.date.DateUtil;
import com.luochaoqun.plus.ideas.xiaoyuandiancan.util.security.DESUtil;
import com.luochaoqun.plus.ideas.xiaoyuandiancan.util.webutils.BaseController;
import com.luochaoqun.plus.ideas.xiaoyuandiancan.util.webutils.http.response.ResponseDataVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Author luochaoqun
 * @Date 2019-12-17 23:33
 **/
@Slf4j
@Controller
public class FileController extends BaseController {
    @Value("file.upload.path")
    private String fileUploadPath;

    @Value("file.upload.imgMaxSize")
    private Integer imgMaxSize;

    @Value("file.upload.imgMinSize")
    private Integer imgMinSize;


    @RequestMapping("/visitImg")
    public void visitImg(HttpServletResponse response, @RequestParam(name = "filePath") String filePath) {
        filePath=DESUtil.decrypt(filePath);
        response.setContentType("image/jpg");
        FileInputStream fileInputStream = null;
        try {
            OutputStream outputStream = response.getOutputStream();
            File file = new File(fileUploadPath + File.separator + filePath);
            fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[512];
            while (fileInputStream.read(bytes) > 0) {
                outputStream.write(bytes);
            }
            outputStream.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }

    }

    @RequestMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response, @RequestParam(name = "filePath") String filePath) {

        filePath= DESUtil.decrypt(filePath);

        String osName = System.getProperty("os.name");
        String splitChar = "";
        //windows
        if(osName.contains("Windows")) {
            splitChar = "\\\\";
        }else {
            splitChar = "/";
        }
        String[] fileSeperatorArr = filePath.split(splitChar);
        if(fileSeperatorArr==null ) {
            try {
                response.getWriter().write("参数格式错误");
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment; filename="+fileSeperatorArr[fileSeperatorArr.length-1]);

        FileInputStream fileInputStream = null;
        try {
            OutputStream outputStream = response.getOutputStream();
            File file = new File(filePath + File.separator + filePath);
            fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[512];
            while (fileInputStream.read(bytes) > 0) {
                outputStream.write(bytes);
            }
            outputStream.flush();
        } catch (IOException e) {
            try {
                response.getWriter().write("文件不存在");
            } catch (IOException e1) {
                log.error(e1.getMessage());
            }
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }

    }

    @RequestMapping(value = "/singlePdf", method = RequestMethod.POST)
    public ResponseDataVo<String> singlePdf(@RequestParam(value = "file", required = true) MultipartFile multipartFile,
                                            HttpServletRequest request) {

        User user = getCurrUser(request);
        if(user==null) {
            return ResponseDataVo.argError("请先登录");
        }

        String uploadFileName = multipartFile.getOriginalFilename();
        String imgType = uploadFileName.split("\\.")[1];

        if (!"pdf".equalsIgnoreCase(imgType)) {
            return ResponseDataVo.argError("只支持pdf类型");
        }

        // 创建目录
        String dateStr = DateUtil.formatDateByPattern(new Date(), "yyyy-MM-dd");
        final String fileDir = fileUploadPath + File.separator + user.getId() + File.separator + dateStr;

        String fileName = UUID.randomUUID().toString();
        final String imgPath = fileDir + File.separator + fileName + "." + imgType;

        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        Runnable uploadRunnable = new uploadFile(fileDir, imgPath, inputStream);

        Executors.newCachedThreadPool().execute(uploadRunnable);

        String filePath =  user.getId() + File.separator
                + dateStr + File.separator + fileName + "." + imgType;
        // 返回文件存放路径
        return ResponseDataVo.success(DESUtil.encrypt(filePath));

    }

    @RequestMapping(value = "/singleImg")
    public ResponseDataVo<String> singleImg(@RequestParam("file") MultipartFile multipartFile,
                                            HttpServletRequest request) {

        User user = getCurrUser(request);
        if(user==null) {
            return ResponseDataVo.argError("请先登录");
        }

        String checkResult = checkFile(multipartFile, imgMaxSize, imgMinSize);

        // 校验文件
        if (StringUtils.isNotEmpty(checkResult)) {
            return ResponseDataVo.argError(checkResult);
        }

        String uploadFileName = multipartFile.getOriginalFilename();
        String imgType = uploadFileName.split("\\.")[1];

        String[] allowImgTypeArr = { "jpg", "png", "jpeg", "gif", "tif","JPG","PNG","JPEG","GIF","TIF" };
        List<String> allowImgTypeList = Arrays.asList(allowImgTypeArr);
        if (!allowImgTypeList.contains(imgType)) {
            return ResponseDataVo.argError("只支持jpg,png,jpeg,gif,tif图片类型");
        }

        // 创建目录
        String dateStr = DateUtil.formatDateByPattern(new Date(), "yyyy-MM-dd");
        final String fileDir = fileUploadPath + File.separator + user.getId() + File.separator + dateStr;

        String fileName = UUID.randomUUID().toString();
        final String imgPath = fileDir + File.separator + fileName + "." + imgType;

        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        Runnable uploadRunnable = new uploadFile(fileDir, imgPath, inputStream);

        Executors.newCachedThreadPool().execute(uploadRunnable);
        String filePath = user.getId() + File.separator
                + dateStr + File.separator + fileName + "." + imgType;
        // 返回文件存放路径
        return ResponseDataVo.success(DESUtil.encrypt(filePath));

    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    private String checkFile(MultipartFile multipartFile, Integer maxSize, Integer minSize) {
        Long fileSize = multipartFile.getSize();

        if (maxSize < fileSize) {
            return "文件大小不能超过" + maxSize + "byte";
        }

        if (minSize > fileSize) {
            return "文件大小不能小于" + minSize + "byte";
        }

        return null;
    }

    class uploadFile implements Runnable {

        private String fileDir;
        private String imgPath;
        private InputStream inputStream;

        public uploadFile(String fileDir, String imgPath, InputStream inputStream) {
            this.fileDir = fileDir;
            this.imgPath = imgPath;
            this.inputStream = inputStream;
        }

        @Override
        public void run() {

            File file = new File(fileDir);
            if (!file.exists()) {
                file.mkdirs();
            }

            OutputStream outputStream = null;

            try {
                outputStream = new FileOutputStream(imgPath);

                // 得到图片的二进制数据，以二进制封装得到数据，具有通用性
                byte[] data = readInputStream(inputStream);
                // new一个文件对象用来保存图片，默认保存当前工程根目录
                File imageFile = new File(imgPath);
                // 创建输出流
                FileOutputStream outStream = new FileOutputStream(imageFile);
                // 写入数据
                outStream.write(data);
                outStream.flush();
                outStream.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        log.error(e.getMessage());
                    }
                }

                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        log.error(e.getMessage());
                    }
                }

            }

        }

    }
}
