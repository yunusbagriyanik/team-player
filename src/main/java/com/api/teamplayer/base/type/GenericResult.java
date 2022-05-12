package com.api.teamplayer.base.type;

import java.util.Objects;

public class GenericResult<T> {
    private T result;
    private ProcessResult processResult;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public ProcessResult getProcessResult() {
        return processResult;
    }

    public void setProcessResult(ProcessResult processResult) {
        this.processResult = processResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericResult<?> that = (GenericResult<?>) o;
        return Objects.equals(result, that.result) && Objects.equals(processResult, that.processResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, processResult);
    }
}
