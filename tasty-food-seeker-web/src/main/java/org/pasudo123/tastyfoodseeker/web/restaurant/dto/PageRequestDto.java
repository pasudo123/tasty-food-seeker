package org.pasudo123.tastyfoodseeker.web.restaurant.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageRequestDto {
    private int page;   // 현재 페이지
    private int size;   // 페이지 당 개수
    private Sort.Direction sort = Sort.Direction.DESC;

    public void setPage(int page) {
        this.page = (page <= 0) ? 1 : page;
    }

    public void setSize(int size) {
        this.size = Math.min(size, 30);
    }

    public PageRequest toPageRequestByIdDesc() {
        return PageRequest.of((page - 1), size, sort, "id");
    }

    public PageRequest toPageRequestByIdAsc() {
        return PageRequest.of((page - 1), size, Sort.Direction.ASC, "id");
    }
}
