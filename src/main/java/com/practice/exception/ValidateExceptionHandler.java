package com.practice.exception;

import com.practice.dto.ResponseJsonEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hzq
 * @date 2019/12/11
 */
@ControllerAdvice(basePackages = "com.practice.controller")
public class ValidateExceptionHandler {

    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity handleConstraintViolationException(MethodArgumentNotValidException cve){
        BindingResult bindingResult = cve.getBindingResult();
        if(bindingResult.hasErrors()){
            for (ObjectError error : bindingResult.getAllErrors()) {
                return ResponseJsonEntity.badRequest(error.getDefaultMessage());
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
