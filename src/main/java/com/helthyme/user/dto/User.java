package com.helthyme.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String gender;
    private Integer birthDate;
    private String weight;
    private String height;
    private Integer bmi;

}
