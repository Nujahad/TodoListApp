package com.todolist.rest;

public class ErrorResponse {
    private int errorCode;
    private String message;
    private Long timeStamp;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public static class ErrorResponseBuilder {
        private int errorCode;
        private String message;
        private Long timeStamp;

        public ErrorResponseBuilder withErrorCode(int errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ErrorResponseBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseBuilder withTimeStamp(Long timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public ErrorResponse build() {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorCode(this.errorCode);
            errorResponse.setMessage(this.message);
            errorResponse.setTimeStamp(this.timeStamp);
            return errorResponse;
        }

    }
}

