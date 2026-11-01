package com.courseerpbackend.demo1.mapper;

import com.courseerpbackend.demo1.dto.security.SignUpPayload;
import com.courseerpbackend.demo1.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
    @Mapping(target = "password",source = "encryptedPassword")
    @Mapping(target = "status",constant = "ACTIVE")
    @Mapping(target = "roleId",source = "roleId")
    User fromSignUpPayloadToUser(SignUpPayload payload,String encryptedPassword,Long roleId);
}
