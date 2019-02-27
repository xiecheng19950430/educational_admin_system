package com.ebay.services;

import com.ebay.mappers.GmCourseMapper;
import com.ebay.models.GmCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GmCourseService {
    @Autowired
    private GmCourseMapper mapper;

    public List<GmCourse> getCourseList(Integer openGrade, String openTerm, Integer isDelete,
                                        String courseName, Integer page, Integer size) {
        Integer start=null;
        if(page!=null)
            start=(page - 1) * size;
        return mapper.getCourseList(openGrade,openTerm,isDelete,courseName,start,size);
    }

    public  int count(Integer openGrade, String openTerm,
                      Integer isDelete, String courseName){
        return mapper.count(openGrade,openTerm,isDelete,courseName);
    }


    public GmCourse findById(int id) {
        return mapper.findById(id);
    }

    public int update(GmCourse gmCourse){
        return mapper.update(gmCourse);
    }

    public int delete(int id){
        return mapper.deleteById(id);
    }
}
