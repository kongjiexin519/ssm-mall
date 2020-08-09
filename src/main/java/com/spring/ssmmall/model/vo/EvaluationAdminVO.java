package com.spring.ssmmall.model.vo;

import com.spring.ssmmall.model.pojo.Product;
import com.spring.ssmmall.model.pojo.User;

import java.util.Date;

public class EvaluationAdminVO {
    private Long evaluationId;

    private String content;

    private Integer score;

    private Date createTime;

    private Long memberId;

    private Long productId;

    private Integer enjoy;

    private String state;

    private String disableReason;

    private Date disableTime;

    private String orderNo;

    private User user;

    private ProductVO productVO;

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getEnjoy() {
        return enjoy;
    }

    public void setEnjoy(Integer enjoy) {
        this.enjoy = enjoy;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getDisableReason() {
        return disableReason;
    }

    public void setDisableReason(String disableReason) {
        this.disableReason = disableReason == null ? null : disableReason.trim();
    }

    public Date getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Date disableTime) {
        this.disableTime = disableTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProductVO getProductVO() {
        return productVO;
    }

    public void setProductVO(ProductVO productVO) {
        this.productVO = productVO;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "EvaluationAdminVO{" +
                "evaluationId=" + evaluationId +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", createTime=" + createTime +
                ", memberId=" + memberId +
                ", productId=" + productId +
                ", enjoy=" + enjoy +
                ", state='" + state + '\'' +
                ", disableReason='" + disableReason + '\'' +
                ", disableTime=" + disableTime +
                ", user=" + user +
                ", productVO=" + productVO +
                '}';
    }
}