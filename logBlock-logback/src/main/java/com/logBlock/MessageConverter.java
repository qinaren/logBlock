package com.logBlock;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ChenTiantao on 2016/10/11.
 */
public class MessageConverter extends ClassicConverter {

    private final static String PHONE_REGEX = "(\\D|^)((130|131|132|133|145|134|135|136|137|138|139|147|150|151|152|153|155|156|157|158|159|170|176|177|178|180|181|182|183|184|185|186|187|188|189)[0-9]{8})(\\D|$)";
    private final static String ID_CARD_REGEX = "[1-9](\\d{5})(18|19|20)(\\d{2})([01]\\d)([0123]\\d)(\\d{3})(\\d|X|x)";
    @Override
    public String convert(ILoggingEvent event) {
        try {
            return convert(event.getFormattedMessage());
        }catch (Exception e){
            return "CfMessageConverter convert Exception:" + e.getMessage() + " info:" + event.getFormattedMessage();
        }
    }
    /**
     * 匹配日志info中的手机号码和身份证号码并脱敏
     * @param info
     * @return
     */
    private String convert(String info){
        Pattern idCardPattern = Pattern.compile(ID_CARD_REGEX);
        Matcher matcher = idCardPattern.matcher(info);
        StringBuffer sb = null;
        while (matcher.find()) {
            if (sb == null) {
                sb = new StringBuffer();
            }
            String data = matcher.group();
            StringBuffer stringBuffer = new StringBuffer(data);
            stringBuffer.replace(6, 14, "********");
            matcher.appendReplacement(sb, stringBuffer.toString());
        }
        if(sb != null){
            matcher.appendTail(sb);
            info = sb.toString();
        }
        Pattern phonePattern = Pattern.compile(PHONE_REGEX);
        matcher = phonePattern.matcher(info);
        StringBuffer sb1 = null;
        while (matcher.find()) {
            if(sb1==null){
                sb1 = new StringBuffer();
            }
            String data = matcher.group(2);
            StringBuffer stringBuffer = new StringBuffer(data);
            stringBuffer.replace(3,7,"****");
            matcher.appendReplacement(sb1,"$1" + stringBuffer.toString() + "$4");
        }
        if(sb1 != null){
            matcher.appendTail(sb1);
            info = sb1.toString();
        }
        return info;
    }
}
