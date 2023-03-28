package com.example.project2.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO<T> {
   private int status;
   private String error;
   private T data;
}
