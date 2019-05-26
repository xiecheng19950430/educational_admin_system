package com.ebay.services;

import com.ebay.mappers.GmStudentAssessmentMapper;
import com.ebay.models.GmStudentAssessment;
import com.ebay.templete.StudentAssessmentTemplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class GmStudentAssessmentService {
    @Autowired
    private GmStudentAssessmentMapper mapper;

    public int insert(GmStudentAssessment assessment) {
        return mapper.insert(assessment);
    }

    public boolean verifyWithOutSelf(String studentNo, String semester, Integer id) {
        return mapper.verifyWithOutSelf(studentNo, semester, id);
    }

    public void excelImport(MultipartFile file) {
        List<GmStudentAssessment> assessments = StudentAssessmentTemplete.temp(file);
        if (!CollectionUtils.isEmpty(assessments)) {
            assessments.forEach(assessment -> {
                boolean isHas = this.verifyWithOutSelf(assessment.getStudentNo(), assessment.getSemester(), null);
                if (!isHas) this.insert(assessment);
            });
        }
    }
}
