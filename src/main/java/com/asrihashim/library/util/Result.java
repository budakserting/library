package com.asrihashim.library.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    
    StatusEnum status;
    String message;
    Object data;
}
