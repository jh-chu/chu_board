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
public class RequestCondition {

    private String type;
    private String keyword;

    public static Pageable chkAndConvertPageable(Pageable pageable) {

        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();

        //기본정렬(createDate->desc)
        Sort sort = Sort.by("createDate").descending();
        sort = sort.and(pageable.getSort());

        page = (page <= 1)? 0 : page-1;


        return PageRequest.of(page, size, sort);
    }

}
