package com.ebay.services;

import com.ebay.common.utils.BeanUtil;
import com.ebay.mappers.GmStudentQualityMapper;
import com.ebay.models.GmStudentQuality;
import com.ebay.templete.StudentQualityTemplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
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


    public GmStudentQuality findByNoAndSemester(String studentNo, String semester) {
        return mapper.findByNoAndSemester(studentNo, semester);
    }


    public int update(GmStudentQuality quality) {
        return mapper.update(quality);
    }


    public void excelImport(MultipartFile file) {
        List<GmStudentQuality> qualities = StudentQualityTemplete.temp(file);
        if (!CollectionUtils.isEmpty(qualities)) {
            qualities.forEach(quality -> {
                GmStudentQuality old = this.findByNoAndSemester(quality.getStudentNo(), quality.getSemester());
                if (ObjectUtils.isEmpty(old)) {
                    this.insert(quality);
                } else {
                    BeanUtil.copyNotNullBean(quality, old);
                    this.update(old);
                }
            });
        }
    }

    public GmStudentQuality findByNoAndSemester(String studentNo, String semester) {
        return mapper.findByNoAndSemester(studentNo, semester);
    }
}
