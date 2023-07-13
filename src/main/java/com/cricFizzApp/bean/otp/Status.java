package com.cricFizzApp.bean.otp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Setter
@Getter
public class Status {

    private HttpStatus httpStatus;
    private String statusMessage;
}
