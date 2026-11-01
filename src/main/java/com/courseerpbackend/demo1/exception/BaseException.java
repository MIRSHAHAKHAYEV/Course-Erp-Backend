package com.courseerpbackend.demo1.exception;

import com.courseerpbackend.demo1.entity.enums.response.ErrorResponseMessages;
import com.courseerpbackend.demo1.entity.enums.response.ResponseMessages;
import com.courseerpbackend.demo1.exception.types.NotFoundExceptionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

import static com.courseerpbackend.demo1.entity.enums.response.ErrorResponseMessages.NOT_FOUND;
import static com.courseerpbackend.demo1.entity.enums.response.ErrorResponseMessages.UNEXPECTED;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException{

    ResponseMessages responseMessages;
    NotFoundExceptionType notFoundData;

    @Override
    public String getMessage() {
        return responseMessages.message();
    }

    public static BaseException of(ResponseMessages responseMessages){
        return BaseException.builder().responseMessages(responseMessages).build();
    }

    public static BaseException unexpected(){
        return of(UNEXPECTED);
    }

    public static BaseException notFound(String target,String field,String value){
        return BaseException.builder()
                .responseMessages(NOT_FOUND)
                .notFoundData(
                        NotFoundExceptionType.of(target, Map.of(field,value))
                )
                .build();
    }
}
