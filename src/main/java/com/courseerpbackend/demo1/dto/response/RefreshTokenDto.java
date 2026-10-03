package com.courseerpbackend.demo1.dto.response;

import com.courseerpbackend.demo1.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshTokenDto {
    boolean isRememberMe;
    User user;
}
