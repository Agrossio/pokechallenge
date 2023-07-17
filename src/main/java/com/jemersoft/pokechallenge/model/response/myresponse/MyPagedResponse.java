package com.jemersoft.pokechallenge.model.response.myresponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyPagedResponse {

    private boolean cached;
    private Object results;
    private String next;
    private String previous;
    private Integer count;

}
