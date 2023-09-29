package com.example.marketplace.utility;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseMessage {
    private String message;
    private Boolean success;
    private HttpStatus status;
}
