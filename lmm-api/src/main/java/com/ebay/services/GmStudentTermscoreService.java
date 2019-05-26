package com.ebay.services;

import com.ebay.common.utils.BeanUtil;
import com.ebay.mappers.GmStudentAssessmentMapper;
import com.ebay.mappers.GmStudentTermscoreMapper;
import com.ebay.models.GmGradeInfo;
import com.ebay.models.GmStudentAssessment;
import com.ebay.templete.StudentAssessmentTemplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class GmStudentTermscoreService {
    @Autowired
    private GmStudentTermscoreMapper mapper;


    public GmGradeInfo findByNoAndSemester(String studentNo, String semester) {
        return mapper.findByNoAndSemester(studentNo, semester);
    }

}
