package com.changgou.file.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.file.pojo.FastDFSFile;
import com.changgou.file.util.FastDFSClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ Author     ：CodeLiu
 * @ Date       ：Created in 2020/09/10 上午 11:00
 * @ Description：
 * @ Modified By：
 */
@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileUploadController {

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping
    public Result upload(@RequestParam("file")MultipartFile file) throws Exception{

        //封装文件信息
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(),//文件名称
                file.getBytes(), //文件内容
                StringUtils.getFilenameExtension(file.getOriginalFilename()));//文件扩展名
        String[] upload = FastDFSClient.upload(fastDFSFile);
        String url = FastDFSClient.getTrackerInfo()+"/"+upload[0]+"/"+upload[1];
        return new Result(true, StatusCode.OK,"文件上传成功！",url);
    }
}
