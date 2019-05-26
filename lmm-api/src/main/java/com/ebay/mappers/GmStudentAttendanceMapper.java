package com.ebay.mappers;

import com.ebay.models.GmStudentAttendance;
import org.apache.ibatis.annotations.Param;

public interface GmStudentAttendanceMapper {
    int insert(GmStudentAttendance attendance);

    boolean checkWithOutSelf(@Param("studentNo")String studentNo, @Param("semester")String semester, @Param("id")Integer id);
}
