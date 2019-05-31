package com.ebay.services;

import com.ebay.mappers.GmStudentTermscoreMapper;
import com.ebay.models.GmGradeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GmStudentTermscoreService {
    @Autowired
    private GmStudentTermscoreMapper mapper;


    public GmGradeInfo findByNoAndSemester(String studentNo, String semester) {
        return mapper.findByNoAndSemester(studentNo, semester);
    }

    public List<GmGradeInfo> query(String studentNo, String semester) {
        return mapper.query(studentNo, semester);
    }
}
