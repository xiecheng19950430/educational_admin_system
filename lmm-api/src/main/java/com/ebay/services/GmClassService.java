package com.ebay.services;

import com.ebay.mappers.GmClassMapper;
import com.ebay.models.GmClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GmClassService {
    @Autowired
    private GmClassMapper mapper;

    public List<GmClass> getClassList(String name, Integer isDelete, Integer status,Integer year, Integer page, Integer size) {
        Integer start=null;
        if(page!=null)
            start=(page - 1) * size;
        return mapper.getClassList(name,isDelete,status,year,start,size);
    }

    public GmClass findById(int id) {
        return mapper.findById(id);
    }


    public  int count(String name,Integer isDelete,Integer status,Integer year){
        return mapper.count(name,isDelete,status,year);
    }

    public int insert(GmClass gmClass){
        return mapper.insert(gmClass);
    }

    public int update(GmClass gmClass){
        return mapper.update(gmClass);
    }

    public int delete(int id){
        return mapper.deleteById(id);
    }

    public void deleteByYear(int year) {
        mapper.deleteByYear(year);
    }

    public Integer getLatestClassNo() {
        return mapper.getLatestClassNo();
    }

    public List<String> queryNameByTeacherId(int teacherId) {
        return mapper.queryNameByTeacherId(teacherId);
    }

    public boolean findByNameWithOutSelf(Integer id, String name) {
        return mapper.findByNameWithOutSelf(id, name);
    }
}
