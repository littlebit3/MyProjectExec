package com.vt.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName random_data_1000w
 */
@TableName(value ="random_data_1000w")
@Data
public class RandomData {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String gender;

    /**
     *
     */
    private String score;

    /**
     *
     */
    private Date birthday;

    /**
     *
     */
    private String phone;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private BigDecimal salary;

    /**
     *
     */
    private String grade;

    /**
     *
     */
    private String city;
}
