package com.api.teamplayer.base.type;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public class ProcessResult {
    private HttpStatus status;
    private int statusCode;
    private String message;

    public static ProcessResult success(String message) {
        ProcessResult result = new ProcessResult();
        result.setStatus(HttpStatus.OK);
        result.setStatusCode(200);
        result.setMessage(message);
        return result;
    }

    public static ProcessResult noContent(String message) {
        ProcessResult result = new ProcessResult();
        result.setStatus(HttpStatus.NO_CONTENT);
        result.setStatusCode(204);
        result.setMessage(message);
        return result;
    }

    public static ProcessResult badRequest(String message) {
        ProcessResult result = new ProcessResult();
        result.setStatus(HttpStatus.BAD_REQUEST);
        result.setStatusCode(400);
        result.setMessage(message);
        return result;
    }

    public static ProcessResult internalServerError(String message) {
        ProcessResult result = new ProcessResult();
        result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        result.setStatusCode(500);
        result.setMessage(message);
        return result;
    }

    public static ProcessResult unauthorized(String message) {
        ProcessResult result = new ProcessResult();
        result.setStatus(HttpStatus.UNAUTHORIZED);
        result.setStatusCode(401);
        result.setMessage(message);
        return result;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessResult that = (ProcessResult) o;
        return statusCode == that.statusCode && status == that.status && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, statusCode, message);
    }
}
