/**
 * @FileName: UploadService
 * @Author: code-fusheng
 * @Date: 2020/5/4 20:19
 * Description: 文件服务工具类
 */
package com.hnxx.zy.clms.common.utils;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
import com.hnxx.zy.clms.common.config.UploadConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@EnableConfigurationProperties(UploadConfig.class)
public class UploadService {

    private Log log = LogFactory.getLog(UploadConfig.class);

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private UploadConfig uploadConfig;

    public String uploadImage(MultipartFile file) {
        // 1.校验文件类型
        String contentType = file.getContentType();
        if (!uploadConfig.getAllowTypes().contains(contentType)) {
            throw new RuntimeException("文件类型不支持");
        }
        try {
            // 3、上传到FastDFS
            // 3.1、获取扩展名
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            // 3.2、上传
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            // 返回路径
            return uploadConfig.getBaseUrl() + storePath.getFullPath();
        } catch (IOException e) {
            log.error("【文件上传】上传文件失败！....{}", e);
            throw new RuntimeException("【文件上传】上传文件失败！" + e.getMessage());
        }
    }

    /**
     * @Description: 上传文件
     * @Param: [file]
     * @return: java.lang.String
     * @Author: CHENLH
     * @Date: 2020-05-19 22:42:18
     */
    public String uploadFile(MultipartFile file) {
        try {
            // 3、上传到FastDFS
            // 3.1、获取扩展名
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            // 3.2、上传
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            // 返回路径
            return uploadConfig.getBaseUrl() + storePath.getFullPath();
        } catch (IOException e) {
            log.error("【文件上传】上传文件失败！....{}", e);
            throw new RuntimeException("【文件上传】上传文件失败！" + e.getMessage());
        }
    }

    /**
     * @Description: 删除文件
     * @Param: [fileUrl]
     * @return: void
     * @Author: CHENLH
     * @Date: 2020-05-19 22:41:51
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            log.warn(e.getMessage());
        }
    }

}
