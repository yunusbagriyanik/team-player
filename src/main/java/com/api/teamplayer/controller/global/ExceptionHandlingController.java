package com.api.teamplayer.controller.global;

import com.api.teamplayer.base.exception.ProcessResultException;
import com.api.teamplayer.base.type.GenericResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = {"com.api.teamplayer"})
public class ExceptionHandlingController {

    @ExceptionHandler({ProcessResultException.class})
    public ResponseEntity<?> handleProcessResultResultException(final ProcessResultException exception) {
        if (exception.isGeneric()) {
            final GenericResult<?> response = new GenericResult<>();
            response.setProcessResult(exception.getProcessResult());

            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.badRequest().body(exception.getProcessResult());
    }


}

