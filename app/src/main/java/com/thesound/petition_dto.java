package com.thesound;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class petition_dto {
    int like;
    List likes;
    String title;
    String description;
    String studentUid;
    String schoolUid;
    String answer;
    Boolean isAnswer;
    String whoAnswer;
    Date createDate;

    public petition_dto(int like, List likes, String title, String description, String studentUid, String schoolUid,String answer, Boolean isAnswer, String whoAnswer, Date createDate){
        this.like=like;
        this.likes=likes;
        this.title=title;
        this.description=description;
        this.studentUid=studentUid;
        this.schoolUid=schoolUid;
        this.answer=answer;
        this.isAnswer=isAnswer;
        this.whoAnswer=whoAnswer;
        this.createDate=createDate;
    }
    public HashMap getInfo(){
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("like",like);
        map.put("likes",likes);
        map.put("title",title);
        map.put("description",description);
        map.put("studentUid",studentUid);
        map.put("schoolUid",schoolUid);
        map.put("answer",answer);
        map.put("isAnswer",isAnswer);
        map.put("whoAnswer",whoAnswer);
        map.put("createDate",createDate);

        return map;
    }
    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public List getLikes() {
        return likes;
    }

    public void setLikes(List likes) {
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudentUid() {
        return studentUid;
    }

    public void setStudentUid(String studentUid) {
        this.studentUid = studentUid;
    }

    public String getSchoolUid() {
        return schoolUid;
    }

    public void setSchoolUid(String schoolUid) {
        this.schoolUid = schoolUid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        isAnswer = answer;
    }

    public String getWhoAnswer() {
        return whoAnswer;
    }

    public void setWhoAnswer(String whoAnswer) {
        this.whoAnswer = whoAnswer;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}