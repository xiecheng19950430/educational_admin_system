package com.ebay.services;

import com.ebay.mappers.GmStudentMapper;
import com.ebay.models.GmStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GmStudentService {
    @Autowired
    private GmStudentMapper mapper;

    public List<GmStudent> getTeacherList(Integer classId, String studentNo, Integer isDelete, String name,
                                          String birthday, Integer page, Integer size) {
        Integer start=null;
        if(page!=null)
            start=(page - 1) * size;
        return mapper.getStudentList(classId,studentNo,isDelete,name,birthday,start,size);
    }

    public GmStudent findById(int id) {
        return mapper.findById(id);
    }

    public GmStudent findByClassId(Integer classId,String role) {
        return mapper.findByClassId(classId,role);
    }

    public  int count(String studentNo,Integer isDelete,String name,String birthday){
        return mapper.count(studentNo,isDelete,name,birthday);
    }

    public int insert(GmStudent gmStudent){
        return mapper.insert(gmStudent);
    }

    public int update(GmStudent gmStudent){
        return mapper.update(gmStudent);
    }

    public int delete(int id){
        return mapper.deleteById(id);
    }

    public List<GmStudent> query(Integer classId, String name, String studentNo) {
        return mapper.query(classId, name, studentNo);
    }
}
