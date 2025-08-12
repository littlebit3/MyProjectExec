package com.vt.controller;


import com.vt.domain.req.ExcelDownloadReq;
import com.vt.service.RandomDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ExcelController {

    @Autowired
    private RandomDataService randomDataService;
    @PostMapping("/excelDownLoad")
    public void excelDownLoad(@RequestBody ExcelDownloadReq req, HttpServletResponse response) {

        randomDataService.downLoadExcel(req,response);
    }
    @GetMapping("test")
    public String test() {
        return "ok";
    }
}
