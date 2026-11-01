package com.courseerpbackend.demo1.dto.response;

import com.courseerpbackend.demo1.entity.enums.response.ResponseMessages;
import com.courseerpbackend.demo1.entity.enums.response.SuccessResponseMessages;
import com.courseerpbackend.demo1.exception.BaseException;
import com.courseerpbackend.demo1.exception.types.NotFoundExceptionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import static com.courseerpbackend.demo1.entity.enums.response.ErrorResponseMessages.NOT_FOUND;
import static com.courseerpbackend.demo1.entity.enums.response.SuccessResponseMessages.SUCCESS;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse <T>{
    HttpStatus status;
    Meta meta;
    T data;

    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder(access = AccessLevel.PRIVATE)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static final class Meta{
        String key;
        String message;

        public static Meta of(String key,String message){
            return Meta.builder()
                    .key(key)
                    .message(message)
                    .build();
        }
        public static Meta of(ResponseMessages responseMessages){
            return of(responseMessages.key(),responseMessages.message());
        }
        public static Meta of(BaseException ex){
            if(ex.getResponseMessages().equals(NOT_FOUND)){
                NotFoundExceptionType notFoundData=ex.getNotFoundData();

                return of(
                        String.format(ex.getResponseMessages().key(),notFoundData.getTarget().toLowerCase()),
                        String.format(ex.getResponseMessages().message(),notFoundData.getTarget().toLowerCase(),notFoundData.getFields().toString())
                );
            }
            return of(ex.getResponseMessages());
        }
    }

    public static <T> BaseResponse<T> success(T data){
        return BaseResponse.<T>builder()
                .status(HttpStatus.OK)
                .data(data)
                .meta(Meta.of(SUCCESS))
                .build();
    }

    public static <T> BaseResponse<T> success(){
        return success(null);
    }

    public static BaseResponse<?> error(BaseException ex){
        return BaseResponse.builder()
                .meta(Meta.of(ex))
                .status(ex.getResponseMessages().status())
                .build();

    }

}
