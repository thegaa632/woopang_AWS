package com.standout.sopang.springex.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

//import java.sql.Date;
import java.util.Date;
import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoVO {

    private Long tno;

    private Long tnoNumber;

    private int goods_id_t_shopping_goods;

    private String title;

    private String content;

    private Date dueDate;

    private String writer;

    private boolean finished;
}
