/**
 * @FileName: UploadController
 * @Author: code-fusheng
 * @Date: 2020/5/4 20:29
 * Description: 文件上传控制类
 */
package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.common.utils.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 文件上传
     * @param file
     * @return url 文件地址
     */
    @RequestMapping("/uploadImage")
    public Result<String> uploadImage(MultipartFile file) {
        String url = uploadService.uploadImage(file);
        return new Result<>("操作成功: 文件上传！", url);
    }
}
