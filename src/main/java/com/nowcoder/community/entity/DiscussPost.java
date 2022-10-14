package com.nowcoder.community.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Date;

/**
 * @ClassName DiscussPost
 * @Description
 * @Author cjx
 * @Date 2022/1/9 23:15
 * @Version 1.0
 */
@Document(indexName = "discusspost")
@Setting(shards = 6,replicas=3)
public class DiscussPost {

    @Id
    private int id;
    @Field(type= FieldType.Integer)
    private int userId;
    //互联网校招->需要建立索引，创建关联，存储时需要尽可能拆分成多个词条，进行匹配，增加搜索范围;搜索时希望“聪明点“搜索
    @Field(type=FieldType.Text,analyzer = "ik_max_word",searchAnalyzer ="ik_smart")
    private String title;
    @Field(type=FieldType.Text,analyzer = "ik_max_word",searchAnalyzer ="ik_smart")
    private String content;
    @Field(type= FieldType.Integer)
    private int type;
    @Field(type= FieldType.Integer)
    private int status;
    @Field(type= FieldType.Date)
    private Date createTime;
    @Field(type= FieldType.Integer)
    private int commentCount;
    @Field(type= FieldType.Double)
    private double score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "DiscussPost{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", createTime=" + createTime +
                ", commentCount=" + commentCount +
                ", score=" + score +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }


}
