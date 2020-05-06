/**
 * @FileName: UploadConfig
 * @Author: code-fusheng
 * @Date: 2020/5/4 20:15
 * Description: 文件上传配置类
 */
package com.hnxx.zy.clms.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "upload")
public class UploadConfig {

    private String baseUrl;
    private List<String> allowTypes;

}
