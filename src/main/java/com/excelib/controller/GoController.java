package com.excelib.controller;

import com.excelib.data.Json;
import main.spring.textrecognition.service.RecognizeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Date;

@Controller
public class GoController {
    private final Log logger = LogFactory.getLog(GoController.class); //处理HEAD类型的”/”请求

    @Autowired
    public RecognizeService service;

    @RequestMapping(value = { "/" }, method = { RequestMethod.HEAD })
    public String head() {
        return "WEB-INF/go.jsp";
    }

    //处理GET类型的"/index"和”/”请求 
    @RequestMapping(value = { "/index", "/" }, method = { RequestMethod.GET })
    public String index(Model model) throws Exception {
        logger.info("======processed by index=======");
        //返回msg参数 
        model.addAttribute("msg", "Go Go Go!");
        return "WEB-INF/go.jsp";
    }

    @RequestMapping(value = "/recognition", method = RequestMethod.POST)
    @ResponseBody
    public String recognition(@RequestParam MultipartFile pics) {
        logger.info("上传文件：" + pics.getOriginalFilename());
        String recognize = service.recognize(pics);
        logger.info("结果：" + recognize);
        return recognize;
    }

    @RequestMapping(value = "/test/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String test(@PathVariable String id) {
        return "test : " + id;
    }

    @RequestMapping(value = "/test/json", method = RequestMethod.POST)
    @ResponseBody
    public String json(Json json) {
        return "json{a=" + json.getA() + ",b=" + json.getB()+",date="+new Date()+"}";
    }

    @RequestMapping(value = "/test/post", method = RequestMethod.POST)
    @ResponseBody
    public String post(String a, String b) {
        return "post{a=" + a + ",b=" + b+",date="+new Date()+"}";
    }

}