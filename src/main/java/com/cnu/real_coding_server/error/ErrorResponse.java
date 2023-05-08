package com.cnu.real_coding_server.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    String code; // 클라이언트랑 약속한 작동
    String message;
}
