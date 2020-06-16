package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.WebSocketServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @FileName: TestController
 * @Author: code-fusheng
 * @Date: 2020/6/14 8:44
 * @version: 1.0
 * Description:
 */

public class TestController {

    @PostMapping("/sendAllWebSocket")
    public String test() {
        String text="你们好！这是websocket群体发送！";
        try {
            WebSocketServer.sendInfo(text);
        }catch (IOException e){
            e.printStackTrace();
        }
        return text;
    }

}
