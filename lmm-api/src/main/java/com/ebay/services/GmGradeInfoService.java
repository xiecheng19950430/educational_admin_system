package com.ebay.services;

import com.ebay.mappers.GmGradeInfoMapper;
import com.ebay.models.GmGradeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GmGradeInfoService {
    @Autowired
    private GmGradeInfoMapper mapper;

    public List<GmGradeInfo> list(Integer page, Integer size) {
        Integer start = null;
        if (page != null)
            start = (page - 1) * size;
        return mapper.list(start, size);
    }

    public int count() {
        return mapper.count();
    }
}
