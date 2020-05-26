package com.hnxx.zy.clms.security.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Component;

/**
 * @program: test_security
 * @description: SendSms
 * @author: nile
 * @create: 2020-05-15 12:28
 **/
@Component
public class SendSms {

    public static void send(String mobile, String code, String templateCode) {
        // 创建DefaultAcsClient实例并初始化  地域ID   RAM账号的AccessKey ID  RAM账号AccessKey Secret
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G6pyEy5dzvC4X2WnFnD",
                "rx3iPHs9iWogLH04cY8Dc5makLmhly");
        IAcsClient client = new DefaultAcsClient(profile);
        // 创建API请求并设置参数
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        //接收短信的手机号码。
        request.putQueryParameter("PhoneNumbers", mobile);
        //短信签名名称
        request.putQueryParameter("SignName", "clms");
        //短信模板ID
        request.putQueryParameter("TemplateCode", templateCode);
        //短信模板变量对应的实际值，JSON格式
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        //上行短信扩展码，无特殊需要此字段的用户请忽略此字段。
        //request.putQueryParameter("SmsUpExtendCode", "90999");
        //外部流水扩展字段。
        //request.putQueryParameter("OutId", "abcdefgh");
        try {
            // 发起请求并处理应答或异常
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
