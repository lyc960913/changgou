package com.changgou.file.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ Author     ：CodeLiu
 * @ Date       ：Created in 2020/09/10 上午 10:22
 * @ Description：对文件信息的封装
 * @ Modified By：
 */
@Data
public class FastDFSFile implements Serializable {

    //文件名字
    private String name;
    //文件内容
    private byte[] content;
    //文件扩展名
    private String ext;
    //文件MD5摘要值
    private String md5;
    //作者
    private String author;

    public FastDFSFile(String name, byte[] content, String ext, String md5, String author) {
        this.name = name;
        this.content = content;
        this.ext = ext;
        this.md5 = md5;
        this.author = author;
    }

    public FastDFSFile(String name, byte[] content, String ext) {
        this.name = name;
        this.content = content;
        this.ext = ext;
    }
}
