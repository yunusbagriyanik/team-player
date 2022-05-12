package com.api.teamplayer.base.exception;

import com.api.teamplayer.base.type.ProcessResult;
import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

public class ProcessResultException extends AbstractException implements GraphQLError {
    private ProcessResult processResult;
    private boolean isGeneric;

    public ProcessResultException(ProcessResult operationResult, boolean isGeneric) {
        this.processResult = operationResult;
        this.isGeneric = isGeneric;
    }

    public ProcessResult getProcessResult() {
        return processResult;
    }

    public void setProcessResult(ProcessResult processResult) {
        this.processResult = processResult;
    }

    public boolean isGeneric() {
        return isGeneric;
    }

    public void setGeneric(boolean generic) {
        isGeneric = generic;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.ValidationError;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return GraphQLError.super.getExtensions();
    }

    @Override
    public String getMessage() {
        return this.processResult.getMessage();
    }
}
