package com.example.weberp.dto;

import lombok.Data;

@Data
public class WeberpDto {

    private int erpNum;
    private String erpId;
    private String erpTitle;
    private String erpContent;

    private String erpFilename;
    private String erpFilepath;
}
