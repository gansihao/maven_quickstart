package com.excelib.controller;

import main.spring.druid.service.DruidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.parsers.DocumentBuilderFactory;

@Controller
public class DBController {

    @Autowired
    DruidService service;

    @RequestMapping(value = { "/dds"}, method = { RequestMethod.GET })
    @ResponseBody
    public String run() {
        service.exe();
        return "success";
    }

    public String security() {
//        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        return "security";
    }
}
