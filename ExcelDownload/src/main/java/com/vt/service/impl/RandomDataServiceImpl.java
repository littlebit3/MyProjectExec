package com.vt.service.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.vt.config.ExcelExportConfig;
import com.vt.domain.model.RandomData;
import com.vt.domain.req.ExcelDownloadReq;
import com.vt.domain.resp.ExcelDownloadResp;
import com.vt.service.RandomDataService;
import com.vt.mapper.RandomDataMapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
* @author Hefeng
* @description 针对表【random_data_1000w】的数据库操作Service实现
* @createDate 2025-08-12 22:32:21
*/
@Service
public class RandomDataServiceImpl extends ServiceImpl<RandomDataMapper, RandomData>
    implements RandomDataService{

    @Resource
    private ExcelExportConfig excelExportConfig;
    @Override
    public void downLoadExcel(ExcelDownloadReq req, HttpServletResponse response) {
        Long countLong = count();
        Integer count = countLong.intValue();
        Integer selectSize = excelExportConfig.getSelectSize();
        Integer pageSize = req.getPageSize();
        Integer pageNum = req.getPageNum();
        if (!excelExportConfig.getPageSize().equals(pageSize)) {
            throw new RuntimeException("配置pageSize有误");
        }
        if (pageSize < selectSize) {
            throw new RuntimeException("pageSize不能小于selectSize");
        }
        if (pageSize % selectSize != 0) {
            throw new RuntimeException("pageSize必须为selectSize的倍数");
        }

        int pages;
        int tempPages = getPages(pageSize,selectSize);
        if (pageSize * pageNum.longValue() >= count()) {
            Integer sizes = count - (pageNum - 1) * pageSize;
            pages = getPages(sizes,selectSize);
        }else {
            pages = tempPages;
        }
        List<ExcelDownloadResp> resps = new ArrayList<>();
        long l = System.currentTimeMillis();
        for (int i = 0; i < pages; i++) {
            PageHelper.startPage(tempPages *(pageNum -1) + i + 1, selectSize);
            List<RandomData> list = list();
            PageHelper.clearPage();
            List<ExcelDownloadResp> respsTemp = list.stream().map(data -> {
                ExcelDownloadResp resp = new ExcelDownloadResp();
                BeanUtils.copyProperties(data, resp);
                return resp;
            }).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            resps.addAll(respsTemp);
        }
        System.out.println("导出: "+req.getPageSize() +"耗时：" + (System.currentTimeMillis() - l));
        try (Workbook workbook = new XSSFWorkbook(); OutputStream outputStream = response.getOutputStream()){

            if (!resps.isEmpty()) {
                ExportParams params = new ExportParams();
                params.setSheetName("导出数据");
                new ExcelExportService().createSheet(workbook, params, ExcelDownloadResp.class, resps);
            }

            // 正确的Content-Type for .xlsx files
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            String fileName = "导出数据" + req.getPageNum() + ".xlsx";
            // 正确设置文件名
            String encodedFileName = java.net.URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
            response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName);

            workbook.write(outputStream);
        } catch (Exception e) {
            System.out.println("导出数据异常");
            throw new RuntimeException(e);
        }
    }

    private int getPages(Integer sheetSize, Integer pageSize) {
        int pages = 0;

        if (sheetSize % pageSize !=0){
            int size = sheetSize /pageSize;
            pages = size + 1;
        }else {
            pages = sheetSize / pageSize;
        }
        return pages;
    }
}




