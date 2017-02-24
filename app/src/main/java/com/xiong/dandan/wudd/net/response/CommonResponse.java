package com.xiong.dandan.wudd.net.response;


/**
 *
 * @param <T>
 */
public class CommonResponse<T>  {

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

    }

}
