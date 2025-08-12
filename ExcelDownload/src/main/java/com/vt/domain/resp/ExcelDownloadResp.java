package com.vt.domain.resp;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExcelDownloadResp {


    @Excel(name = "ID")
    private Integer id;


    @Excel(name = "姓名")
    private String name;


    @Excel(name = "性别")
    private String gender;


    @Excel(name = "分数")
    private String score;


    @Excel(name = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;


    @Excel(name = "手机号码")
    private String phone;


    @Excel(name = "邮箱")
    private String email;


    @Excel(name = "薪资")
    private BigDecimal salary;


    @Excel(name = "等级")
    private String grade;


    @Excel(name = "城市")
    private String city;
}
