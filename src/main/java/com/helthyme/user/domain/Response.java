package com.helthyme.user.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<DataType> {
    private boolean isSuccess;
    private String message;
    private DataType data;

}
