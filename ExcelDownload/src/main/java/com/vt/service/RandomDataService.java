package com.vt.service;

import com.vt.domain.model.RandomData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vt.domain.req.ExcelDownloadReq;

import javax.servlet.http.HttpServletResponse;

/**
* @author Hefeng
* @description 针对表【random_data_1000w】的数据库操作Service
* @createDate 2025-08-12 22:32:21
*/
public interface RandomDataService extends IService<RandomData> {

    void downLoadExcel(ExcelDownloadReq req, HttpServletResponse response);
}
