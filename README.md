# logBlock
这个项目是对日志中的手机号和身份证号进行脱敏处理，手机号和身份证号都是中国的
比如186****4524,121542********80425
将logback.xml中的encoder由原来的默认的PatternLayoutEncoder换成com.logBlock.PatternLayoutEncoder
<encoder class="com.logBlock.PatternLayoutEncoder">
    <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level - [%thread] - %logger{36}[%L] - [seq:%X{requestSeq}]%msg%n</pattern>
</encoder>
