package com.ebay.services;

import com.ebay.mappers.GmStudentBodyStatusMapper;
import com.ebay.models.GmStudentBodyStatus;
import com.ebay.templete.StudentBodyStatusTemplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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

    public void excelImport(MultipartFile file) {
        List<GmStudentBodyStatus> bodyStatuses = StudentBodyStatusTemplete.temp(file);
        if (!CollectionUtils.isEmpty(bodyStatuses)) {
            bodyStatuses.forEach(bodyStatus -> {
                boolean isHas = this.findByStudentNoWithOutSelf(bodyStatus.getStudentNo(), bodyStatus.getSemester(), null);
                if (!isHas) this.insert(bodyStatus);
            });
        }
    }
}
