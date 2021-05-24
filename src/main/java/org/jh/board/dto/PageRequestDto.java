package org.jh.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@Data
@AllArgsConstructor
public class PageRequestDto {

    private int page;
    private int size;

    public PageRequestDto() {
        page = 1;
        size = 10;
    }

    public Pageable getPageable(Sort sort) {
        if(page <= 1) page = 1;
        return PageRequest.of(page - 1, size, sort);
    }
}
