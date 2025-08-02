package com.vt.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 *
 * @TableName t_comment_0
 */
@TableName(value ="t_comment")
@Data
public class Comment {
    /**
     *
     */
    @TableId
    private Long commentId;

    /**
     *
     */
    private String packageName;

    /**
     *
     */
    private String operatorId;

    /**
     *
     */
    private String content;

    /**
     *
     */
    private Date modifyTime;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private String descz;

}
