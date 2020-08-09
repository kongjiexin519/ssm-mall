package com.spring.ssmmall.common;

import com.google.common.collect.Sets;
import com.spring.ssmmall.exception.MallException;
import com.spring.ssmmall.exception.MallExceptionEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Constant {
    public static final String SALT = "cnishf34po5";
    public static final String MALL_USER = "mall_user";

    public static String FILE_UPLOAD_DIR;
    public static Integer EVALUATION_EXPIRE_TIME;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }

    @Value("${evaluation.expire.time}")
    public void setEvaluationExpireTime(Integer evaluationExpireTime) {
        EVALUATION_EXPIRE_TIME = evaluationExpireTime;
    }

    public interface ProductListOrderBy {
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price desc", "price asc");
    }

    public interface SaleStatus{
        int NOT_SALE = 0;
        int SALE = 1;
    }

    public interface Cart{
        int UN_CHECKED = 0;
        int CHECKED = 1;
    }

    public enum OrderStatusEnum{
        CANCEL(0, "用户已取消"),
        NOT_PAID(10, "未付款"),
        PAID(20, "已付款"),
        DELIVERED(30, "已发货"),
        FINISHED(40, "交易完成");

        private int code;
        private String value;

        OrderStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public static OrderStatusEnum codeOf(int code) {
            for (OrderStatusEnum orderStatusEnum : values()) {
                if (orderStatusEnum.getCode() == code) {
                    return orderStatusEnum;
                }
            }
            throw new MallException(MallExceptionEnum.NOT_ENUM);
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
