package com.ebay.services;

import com.ebay.mappers.GmStudentQualityMapper;
import com.ebay.models.GmStudentQuality;
import com.ebay.templete.StudentQualityTemplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class GmStudentQualityService {
    @Autowired
    private GmStudentQualityMapper mapper;

    public int insert(GmStudentQuality quality) {
        return mapper.insert(quality);
    }

    public boolean findByStudentNoWithOutSelf(String studentNo, String semester, Integer id) {
        return mapper.findByStudentNoWithOutSelf(studentNo, semester, id);
    }

    public void excelImport(MultipartFile file) {
        List<GmStudentQuality> qualities = StudentQualityTemplete.temp(file);
        if (!CollectionUtils.isEmpty(qualities)) {
            qualities.forEach(quality -> {
                boolean isHas = this.findByStudentNoWithOutSelf(quality.getStudentNo(), quality.getSemester(), null);
                if (!isHas) this.insert(quality);
            });
        }
    }

    public GmStudentQuality findByNoAndSemester(String studentNo, String semester) {
        return mapper.findByNoAndSemester(studentNo, semester);
    }
}
