#!/bin/sh
mvn install:install-file -Dfile=E:\workspace\good_pet\LC_JAVA_API\LMM_API_JAVA\lib\alipay-sdk-20170117151523.jar -DgroupId=alipay -DartifactId=alipay-sdk -Dversion=20170117151523 -Dpackaging=jar -DgeneratePom=true -DcreateChecksum=true
mvn install:install-file -Dfile=E:\workspace\good_pet\LC_JAVA_API\LMM_API_JAVA\lib\aliyun-java-sdk-dysmsapi-1.0.0-SANPSHOT.jar -DgroupId=com.aliyun -DartifactId=aliyun-java-sdk-dysmsapi -Dversion=1.0.0-SANPSHOT -Dpackaging=jar -DgeneratePom=true -DcreateChecksum=true
mvn install:install-file -Dfile=E:\workspace\good_pet\LC_JAVA_API\LMM_API_JAVA\lib\aliyun-java-sdk-core-3.2.3.jar -DgroupId=com.aliyun -DartifactId=aliyun-java-sdk-core -Dversion=3.2.3 -Dpackaging=jar -DgeneratePom=true -DcreateChecksum=true