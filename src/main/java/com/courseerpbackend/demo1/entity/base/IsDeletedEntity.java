package com.courseerpbackend.demo1.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
@NoArgsConstructor
@SuperBuilder
public class IsDeletedEntity {

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    Boolean isDeleted = false;
}
