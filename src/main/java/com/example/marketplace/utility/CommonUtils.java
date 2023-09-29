package com.example.marketplace.utility;

import com.example.marketplace.dto.UserDTO;
import com.example.marketplace.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class CommonUtils {

    public static <U,V> PageableResponse<V> getPageableResponse(Page<U> page, Class<V> type){
        List<U> entity = page.getContent();

        List<V> entityDTOList = entity.stream().map(object -> new ModelMapper().map(object, type)).collect(Collectors.toList());

        PageableResponse<V> pageableResponse = new PageableResponse<>();

        pageableResponse.setContent(entityDTOList);
        pageableResponse.setPageNumber(page.getNumber()+1);
        pageableResponse.setPageSize(page.getSize());
        pageableResponse.setTotalElements(page.getTotalElements());
        pageableResponse.setTotalPages(page.getTotalPages());
        pageableResponse.setLastPage(page.isLast());

        return pageableResponse;
    }
}
