package com.ebay.services;

import com.ebay.common.utils.BeanUtil;
import com.ebay.mappers.GmStudentBodyStatusMapper;
import com.ebay.models.GmStudentBodyStatus;
import com.ebay.templete.StudentBodyStatusTemplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class GmStudentBodyStatusService {
    @Autowired
    private GmStudentBodyStatusMapper mapper;

    public int insert(GmStudentBodyStatus bodyStatus) {
        return mapper.insert(bodyStatus);
    }

    public boolean findByStudentNoWithOutSelf(String studentNo, String semester, Integer id) {
        return mapper.findByStudentNoWithOutSelf(studentNo, semester, id);
    }

    public GmStudentBodyStatus findByNoAndSemester(String studentNo, String semester) {
        return mapper.findByNoAndSemester(studentNo, semester);
    }

    public int update(GmStudentBodyStatus bodyStatuses) {
        return mapper.update(bodyStatuses);
    }

    public void excelImport(MultipartFile file) {
        List<GmStudentBodyStatus> bodyStatuses = StudentBodyStatusTemplete.temp(file);
        if (!CollectionUtils.isEmpty(bodyStatuses)) {
            bodyStatuses.forEach(bodyStatus -> {
                GmStudentBodyStatus old = this.findByNoAndSemester(bodyStatus.getStudentNo(), bodyStatus.getSemester());
                if (ObjectUtils.isEmpty(old)) {
                    this.insert(bodyStatus);
                } else {
                    BeanUtil.copyNotNullBean(bodyStatus, old);
                    this.update(old);
                }
            });
        }
    }
}
