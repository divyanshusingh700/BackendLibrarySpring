package org.truecodes.DigitalLibrary.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.truecodes.DigitalLibrary.model.User;
import org.truecodes.DigitalLibrary.model.UserStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserRequest {


    private String userName;
    @NotBlank(message = "user phoneNo should not be blank")
    private String phoneNo;

    private String email;

    private String address;

    public User toUser() {
        return User
                .builder()
                .name(this.userName)
                .email(this.email)
                .phoneNo(this.phoneNo)
                .address(this.address)
                .userStatus(UserStatus.ACTIVE)
                .build();
    }
}
