package com.appnotes.vnote.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "vnotedata")
public class Vnotedata{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private long id;

    @Column (name = "topic")
    private String topic;

    @Column(name = "content",columnDefinition = "TEXT")
    private String content;

    @Column(name = "group")
    private String group;

    @Column(name = "filename")
    private String filename;

    @Column(name = "format")
    private String format;

//    @Lob
//    @Column (name = "file")
//    private byte[] file;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "entrydate")
    @ApiModelProperty(hidden = true)
    private Date entrydate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateddate")
    @ApiModelProperty(hidden = true)
    private Date updateddate;

//    public Vnotedata(String topic, String content, String group, String filename, String format, byte[] file) {
//        this.topic = topic;
//        this.content = content;
//        this.group = group;
//        this.filename = filename;
//        this.format = format;
//        this.file = file;
//    }
}