package com.example.marketplace.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.aspectj.weaver.ast.Not;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String id;

    @Size(min=3, max=8, message="Invalid username")
    @NotBlank
    private String username;

    @Size(min=1, max=15, message="Invalid firstName")
    @NotBlank
    private String firstName;

    @Size(min=1, max=15, message="Invalid lastName")
    @NotBlank
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email")
    @NotBlank
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message="Passwords must contain: a minimum of 1 lower case letter [a-z] and; a minimum of 1 upper case letter [A-Z] and; a minimum of 1 numeric character [0-9] and; a minimum of 1 special character and; must be 8 char long")
    @NotBlank
    private String password;

    @NotBlank
    private String gender;

    private String profileImage;

}
