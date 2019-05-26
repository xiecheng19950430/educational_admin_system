create table gm_class
(
    id          int auto_increment
        primary key,
    classNo     varchar(50)   null comment '班级编号',
    name        varchar(50)   null comment '班级名称',
    year        int           null comment '年份',
    amount      int           null comment '人数',
    status      int           null comment '班级状态，0:在校，1：已毕业',
    createAt    datetime      null,
    updateAt    datetime      null,
    teacherId   int           null comment '班主任id',
    isDelete    int default 0 null comment '是否删除，1：删除，0：未删除',
    teacherName varchar(50)   null comment '班主任名字'
)
    charset = utf8;

create table gm_class_has_gm_teacher
(
    gm_class_id   int not null,
    gm_teacher_id int not null,
    primary key (gm_class_id, gm_teacher_id)
)
    charset = utf8;

create table gm_course
(
    id          int           not null
        primary key,
    courseNo    varchar(50)   null comment '课程编号',
    studentId   int           not null comment '学生id',
    courseName  varchar(100)  null comment '课程名称',
    openGrade   int           null comment '开设年级',
    openTerm    varchar(50)   null comment '开设学期，上学期：lastTerm,下学期：nextTerm',
    createAt    datetime      null,
    updateAt    datetime      null,
    classAt     datetime      null comment '建课时间',
    type        int           null comment '课程类型，1：主修，0：辅修',
    status      int           null comment '课程性质，1：正常，0：停用',
    description varchar(500)  null comment '课程描述',
    isDelete    int default 0 null
)
    charset = utf8;

create table gm_gradeinfo
(
    id             int auto_increment
        primary key,
    gradeNo        varchar(50)   not null comment '成绩编号',
    courseName     varchar(50)   null comment '课程名称',
    studentNo      varchar(50)   null comment '学号',
    name           varchar(50)   null comment '学生姓名',
    grade_ordinary int           null comment '平时成绩',
    grade_mid      int           null comment '期中成绩',
    grade_final    int           null comment '期末成绩',
    grade_all      int           null comment '总评',
    classId        int           not null comment '班级Id',
    teacherId      int           not null comment '教师Id',
    createAt       datetime      null,
    updateAt       datetime      null,
    isPass         int           null comment '是否及格，0：不及格，1：及格',
    term           varchar(50)   null comment '学期，上学期：lastTerm,下学期：nextTerm',
    schoolYear     int           null comment '学年',
    isDelete       int default 0 null comment '是否删除，1：删除，0：未删除'
)
    charset = utf8;

create table gm_student
(
    id        int auto_increment
        primary key,
    classId   int           not null comment '学生所在班级id',
    studentNo varchar(50)   null comment '学号',
    name      varchar(50)   null comment '学生姓名',
    sex       varchar(11)   null comment '性别',
    birthday  datetime      null,
    createAt  datetime      null,
    updateAt  datetime      null,
    isDelete  int default 0 null
)
    charset = utf8;

create table gm_student_assessment
(
    id                 int auto_increment
        primary key,
    studentNo          varchar(50)  null,
    name               varchar(50)  null,
    conduction         varchar(50)  null,
    performance        varchar(500) null,
    rewardsPunishments varchar(50)  null,
    comment            varchar(500) null,
    semester           varchar(50)  null,
    createAt           timestamp    null,
    updateAt           timestamp    null
);

create table gm_student_bodystatus
(
    id           int auto_increment
        primary key,
    studentNo    varchar(50)  null,
    name         varchar(50)  null,
    height       varchar(50)  null,
    weight       varchar(50)  null,
    leftVision   varchar(50)  null,
    rightVision  varchar(50)  null,
    healthStatus varchar(500) null,
    semester     varchar(50)  null,
    createAt     timestamp    null,
    updateAt     timestamp    null
);

create table gm_student_sub
(
    id            int auto_increment
        primary key,
    studentNo     varchar(50) null,
    className     varchar(50) null,
    name          varchar(50) null,
    sex           varchar(50) null,
    idCard        varchar(50) null,
    birthday      varchar(50) null,
    inSchoolTime  varchar(50) null,
    outSchoolTime varchar(50) null,
    inScore       varchar(50) null,
    sourceSchool  varchar(50) null,
    political     varchar(50) null,
    father        varchar(50) null,
    fatherPhone   varchar(50) null,
    mother        varchar(50) null,
    motherPhone   varchar(50) null,
    other         varchar(50) null,
    otherPhone    varchar(50) null,
    status        varchar(50) null,
    semester      varchar(50) null,
    createAt      timestamp   null,
    updateAt      timestamp   null
);

create table gm_teacher
(
    id        int auto_increment
        primary key,
    workNo    varchar(50)   null comment '工号',
    name      varchar(100)  null comment '姓名',
    loginId   varchar(50)   not null comment '登陆账号',
    password  varchar(50)   null comment '登陆密码',
    sex       varchar(11)   null comment '性别',
    birthday  datetime      null comment '出生日期',
    hiredate  datetime      null comment '入职日期',
    position  varchar(50)   null comment '职称',
    phone     varchar(50)   null comment '联系电话',
    createAt  datetime      null,
    updateAt  datetime      null,
    role      varchar(50)   null comment '角色，超级管理员为superAdmin，其他为角色id（多个用"，"隔开）,班主任classTeacher，系统管理员sysAdmin,教务管理员eduAdmin，教学领导teachLeader',
    isDelete  int default 0 null comment '是否删除，1：删除，0：未删除',
    classId   int           null comment '班级Id',
    roleIds   varchar(500)  null comment '绑定角色id，多个用“，”隔开',
    roleNames varchar(500)  null comment '绑定角色名称，多个用“，”隔开'
)
    charset = utf8;

create table role_module_relation
(
    id       int auto_increment
        primary key,
    roleId   int null,
    moduleId int null
);

create table teacher_role_relation
(
    id        int auto_increment
        primary key,
    teacherId int null,
    roleId    int null
);

create table user_module
(
    id         int auto_increment
        primary key,
    moduleName varchar(50)  null comment '模块名称',
    url        varchar(255) null comment '路径',
    pid        int          null comment '父模块'
)
    charset = utf8;

create table user_role
(
    id         int auto_increment
        primary key,
    role       varchar(50)  not null comment '角色代码',
    roleName   varchar(50)  null comment '角色名',
    moduleIds  varchar(500) null comment '模块Ids',
    moduleUrls varchar(500) null comment '模块路径',
    type       int          null comment '类型1：系统角色（不可更改），2自定义角色'
)
    charset = utf8;

