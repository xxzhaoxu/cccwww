package com.echo.mobileweb.common;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import java.io.IOException;

public class MessageSender {

    // 短信应用 SDK AppID
    static  int appid = 1400229369; // SDK AppID 以1400开头
    // 短信应用 SDK AppKey
    static  String appkey = "7f87e5d51cf86dd30045f19f6a10bbca";

    // 短信模板 ID，需要在短信应用中申请
    static int templateId = 374576; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请


    public static Boolean send(String phonenum,String smscode) {
        new Thread(()->{
            String[] params = {smscode};

            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = null;  // 签名参数未提供或者为空时，会使用默认签名发送短信
            try {
                result = ssender.sendWithParam("86", phonenum,
                        templateId, params, "", "", "");
            } catch (HTTPException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(result);

        }).start();
        return true;
    }
}
