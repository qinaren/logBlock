# logBlock
这个项目是对日志中的手机号和身份证号进行脱敏处理，手机号和身份证号都是中国的</br>
11位手机号码保留前3后4，其他替换成星号</br>
18位身份证号码保留前6后4，其他替换成星成</br>
将logback.xml中的encoder由原来的默认的PatternLayoutEncoder换成com.logBlock.PatternLayoutEncoder</br>
