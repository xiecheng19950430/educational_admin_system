package com.ebay.models;

import lombok.Data;

import java.util.Date;

@Data
public class GmStudent {
    private Integer id;
    private Integer classId;
    private String name;
    private String studentNo;
    private String sex;
    private Integer isDelete;
    private Date birthday;
    private Date createAt;
    private Date updateAt;
}
