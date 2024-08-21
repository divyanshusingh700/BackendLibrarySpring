package org.truecodes.DigitalLibrary.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TxnRequest {
    @NotBlank(message = "user phoneNo should not be blank")
    private String userPhoneNo;

    @NotBlank(message = "user bookNo should not be blank")
    private String bookNo;

}
