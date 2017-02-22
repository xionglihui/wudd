package com.xiong.dandan.wudd.net.response;


import com.xiong.dandan.wudd.entity.BaseEntity;

/**
 * Created by wangyy on 2015/7/16.
 */
public class CommonResponse<T> extends BaseEntity {

    private Result<T> result;

    public Result<T> getResult() {
        return result;
    }

    public void setResult(Result<T> result) {
        this.result = result;
    }

    public class Result<T> {
        /**
         * message : 操作成功!
         * success : true
         */
        private String message;
        private boolean success;
        private boolean error;
        private String code;
        private T data;


        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isError() {
            return error;
        }

        public void setError(boolean error) {
            this.error = error;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public BaseEntity saveInfo(CommonResponse response) {
            return response;
        }
    }

}
