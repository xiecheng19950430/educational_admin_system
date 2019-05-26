package com.ebay.services;

import com.ebay.common.utils.BeanUtil;
import com.ebay.mappers.GmStudentAssessmentMapper;
import com.ebay.models.GmStudentAssessment;
import com.ebay.templete.StudentAssessmentTemplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
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

    public GmStudentAssessment findByNoAndSemester(String studentNo, String semester) {
        return mapper.findByNoAndSemester(studentNo, semester);
    }

    public int update(GmStudentAssessment assessment) {
        return mapper.update(assessment);
    }

    public void excelImport(MultipartFile file) {
        List<GmStudentAssessment> assessments = StudentAssessmentTemplete.temp(file);
        if (!CollectionUtils.isEmpty(assessments)) {
            assessments.forEach(assessment -> {
                GmStudentAssessment old = this.findByNoAndSemester(assessment.getStudentNo(), assessment.getSemester());
                if (ObjectUtils.isEmpty(old)) {
                    this.insert(assessment);
                } else {
                    BeanUtil.copyNotNullBean(assessment, old);
                    this.update(old);
                }
            });
        }
    }
}
