package com.spring.ssmmall.model.pojo;

import java.util.Date;

public class FavoriteState {
    private Long rsId;

    private Long productId;

    private Long userId;

    private Integer favoriteState;

    private Date createTime;

    public Long getRsId() {
        return rsId;
    }

    public void setRsId(Long rsId) {
        this.rsId = rsId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getFavoriteState() {
        return favoriteState;
    }

    public void setFavoriteState(Integer favoriteState) {
        this.favoriteState = favoriteState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}