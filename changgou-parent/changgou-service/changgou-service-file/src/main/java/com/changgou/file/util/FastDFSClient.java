package com.changgou.file.util;

import com.changgou.file.pojo.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


/**
 * @ Author     ：CodeLiu
 * @ Date       ：Created in 2020/09/10 上午 10:28
 * @ Description：文件操作（上传，下载，删除，查找tracker、storage等）工具类
 * @ Modified By：
 */
public class FastDFSClient {

    static {
        //获取文件路径
        String fileName = new ClassPathResource("fdfs_client.conf").getPath();
        try {
            //根据文件路径获取tracker
            ClientGlobal.init(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     * @param fastDFSFile 文件信息的封装
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception{

        //附加参数
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("auther",fastDFSFile.getAuthor());
        StorageClient storageClient = createStorageClient();
        /**
         * 通过storageClient访问storage，实现文件上传，并且获取文件上传后的存储信息
         * 1文件字节数组
         * 2文件扩展名
         * 3附加参数
         * upload_file[0] 文件上传所存储的storage组的名字 group1
         * upload_file[1] 文件存储到storage上的名字 M00/02/44
         */
        String[] upload_file = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), meta_list);
        return upload_file;
    }

    /**
     * 获取文件信息
     * @param groupName 文件的组名 group1
     * @param remoteFileName 文件存储名  M00/00/00/wKjIgF9ZxeqAQRG6AARC0WHiWJk894.jpg
     * @return
     */
    public static FileInfo getFiles(String groupName, String remoteFileName) throws Exception{
        StorageClient storageClient = createStorageClient();
        return storageClient.get_file_info(groupName,remoteFileName);
    }

    /**
     * 文件下载
     * @param groupName
     * @param remoteFileName
     * @throws Exception
     */
    public static InputStream downLoadFile(String groupName, String remoteFileName) throws Exception{
        StorageClient storageClient = createStorageClient();
        byte[] bytes = storageClient.download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(bytes);
    }

    /**
     * 删除文件
     * @param groupName
     * @param remoteFileName
     * @throws Exception
     */
    public static  void deleteFile(String groupName, String remoteFileName) throws Exception{
        StorageClient storageClient = createStorageClient();
        storageClient.delete_file(groupName,remoteFileName);
    }

    /**
     * 获取storage信息
     * @return
     * @throws Exception
     */
    public static StorageServer getStorages() throws Exception{
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorage(trackerServer);
    }
    /**
     * 获取storage IP和port
     * @return
     * @throws Exception
     */
    public static StorageServer getStorageIP(String groupName, String remoteFileName) throws Exception{
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getFetchStorage(trackerServer,groupName,remoteFileName);
    }
    public static String getTrackerInfo() throws Exception{
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        String ip = trackerServer.getInetSocketAddress().getHostString();
        int http_port = ClientGlobal.getG_tracker_http_port();
        return "http://"+ip+":"+http_port;
    }
    /**
     * 生成一个StorageClient对象
     * @return
     * @throws Exception
     */
    public static StorageClient createStorageClient() throws Exception{
        //创建一个Tracker访问的客户端对象TrackerClient
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient访问TrackerServer服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer的连接信息获取storage的链接信息，创建storageClient对象存储链接信息
        return new StorageClient(trackerServer,null);
    }
    public static void main(String[] args) throws Exception{
        /*FileInfo fileInfo = getFiles("group1", "M00/00/00/wKjIgF9ZxeqAQRG6AARC0WHiWJk894.jpg");
        System.out.println(fileInfo.getSourceIpAddr()+""+fileInfo.getFileSize());*/
        /*//文件下载
        InputStream is = downLoadFile("group1", "M00/00/00/wKjIgF9ZxeqAQRG6AARC0WHiWJk894.jpg");
        //把文件写到本地磁盘
        OutputStream os = new FileOutputStream("G:/1.jpg");
        byte[] bytes = new byte[1024];

        while (is.read(bytes)!= -1){
            os.write(bytes);
        }
        os.flush();
        os.close();
        is.close();*/
        deleteFile("group1", "M00/00/00/wKjIgF9Z4CGAenjIAASj8ShtnvQ014.jpg");
    }
}
