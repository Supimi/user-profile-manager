package com.helthyme.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String gender;
    private Long birthdate;
    private String weight;
    private String height;
    private Integer bmi;

}
