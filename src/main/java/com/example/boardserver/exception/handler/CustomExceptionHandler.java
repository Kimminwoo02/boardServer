package com.example.boardserver.exception.handler;

import com.example.boardserver.dto.response.CommonResponse;
import com.example.boardserver.exception.BoardServerException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({RuntimeException.class}) // RuntimeException 에러가 났을 때 처리하는 메서드
    public ResponseEntity<Object> handlerRuntimeException(RuntimeException e){
        CommonResponse commonResponse = new CommonResponse(HttpStatus.OK, "RuntimeException",e.getMessage(),e.getMessage());
        return new ResponseEntity<>(commonResponse, new HttpHeaders(), commonResponse.getStatus());
    }

    @ExceptionHandler({BoardServerException.class}) // RuntimeException 에러가 났을 때 처리하는 메서드
    public ResponseEntity<Object> handleBoardServerException(BoardServerException e){
        CommonResponse commonResponse = new CommonResponse(HttpStatus.OK, "BoardServerException",e.getMessage(),e.getMessage());
        return new ResponseEntity<>(commonResponse, new HttpHeaders(), commonResponse.getStatus());
    }

    @ExceptionHandler({Exception.class}) // RuntimeException 에러가 났을 때 처리하는 메서드
    public ResponseEntity<Object> handleException(Exception e){
        CommonResponse commonResponse = new CommonResponse(HttpStatus.OK, "BoardServerException",e.getMessage(),e.getMessage());
        return new ResponseEntity<>(commonResponse, new HttpHeaders(), commonResponse.getStatus());
    }
}
