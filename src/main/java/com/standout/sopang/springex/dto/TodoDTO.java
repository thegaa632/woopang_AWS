package com.standout.sopang.springex.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
//import java.sql.Date;
import java.util.Date;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    private Long tno;

    private Long tnoNumber;

    private int goods_id_t_shopping_goods;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    // 날짜를 json 형식으로 보낼때 쓰는 두가지 형식
    // 스프링 부트에서 제공하는 @DateTimeFormat, jackson 라이브러리를 사용하는 @JsonFormat
    // 여기서 jackson의 경우
    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")

    // JsonFormat.Shape.STRING 옵션은 날짜를 문자열로 변환하도록 지정하며,
    // pattern 속성을 사용하여 원하는 날짜 및 시간 형식을 지정할 수 있습니다. 여기서 'T'와 띄어쓰기 있는데 띄어쓰기의 경우 값이 잘못 불러올수 있는경우가 있음
    // timezone 속성은 날짜의 표시를 위한 시간대를 설정합니다.
    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm:ss", timezone = "Asia/Seoul")
    private Date dueDate;

    private boolean finished;

    @NotEmpty
    private String writer;

}
