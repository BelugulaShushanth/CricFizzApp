package com.cricFizzApp.bean.otp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class OTPBean {

    private Long transactionId;

    private String mailId;

    private Status mailStatus;

    @JsonIgnore
    private String otp;
}
