package com.exp.demo.apidemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.exp.demo.apidemo.model.InfoModel;
import com.exp.demo.apidemo.model.ResponseObject;

/**
 * InfoController
 * 用于提供一些基本信息的RestAPI
 * 
 * @author akechi
 * @version 0.0.1
 * @since 2023-04-13
 */
@RestController
@RequestMapping("/api/info")
public class InfoController {

    /**
     * 动作确认用RestAPI，直接返回 “Version 0.0.1”
     * 
     * @param 无
     * @return String
     */
    @GetMapping("/version")
    @ResponseBody
    public ResponseObject getVersion() {

        String datetime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        InfoModel info = new InfoModel("Version 0.0.1", datetime);

        return new ResponseObject(0, "OK", info);
    }

    /**
     * 获取当前服务器日期时间
     */
    @GetMapping("/datetime")
    @ResponseBody
    public ResponseObject getDateTime() {
        String datetime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        return new ResponseObject(0, "OK", datetime);
    }

}
