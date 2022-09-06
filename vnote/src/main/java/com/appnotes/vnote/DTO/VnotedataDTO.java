package com.appnotes.vnote.DTO;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VnotedataDTO {

    private long id;
    public String topic;
    private String content;
    private String group;
    private String filename;
    private String format;
    private Date entrydate;
    private Date updateddate;

}
