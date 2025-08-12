package com.vt.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ExcelExportConfig {

    //单个excel导出的数据量
    private Integer pageSize = 100;

    //每个excel进行批量查询，每次查询的数据量
    private Integer selectSize = 100;


}
