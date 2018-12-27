package com.example.dc.myapplication;

import org.junit.Test;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    //命名空间
    String nameSpace = "http://WebXml.com.cn/";
    //地址
    String url = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";

    //junit 测试  java测试
    @Test
    public void test() throws IOException, XmlPullParserException {
        //System.out.println("测试运行");
        //mvp  okhttp retrofit rxjava
        //mvp -> 便于分开测试s
        //Log.i("TEST", "test");

        String methodName = "getSupportCity";

        //创建HttpTransportSe对象：请求url地址
        HttpTransportSE httpTransportSE = new HttpTransportSE(url);
        //创建SoapObject对象：命名空间，调用方法名
        SoapObject soapObject = new SoapObject(nameSpace, methodName);
        soapObject.addProperty("byProvinceName", "南洲");
        //传递参数  没有
        //Soap序列化对象：版本
        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        //设置
        soapSerializationEnvelope.dotNet = true;//是否支持.net
        //设置输入的简单对象
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        httpTransportSE.debug = true;//开启调试
        //执行调用: 命名空间 + 调用方法
        httpTransportSE.call(nameSpace + methodName, soapSerializationEnvelope);
        //获取结果
        if (soapSerializationEnvelope.getResponse() != null) {
            SoapObject bodyIn = (SoapObject) soapSerializationEnvelope.bodyIn;
            SoapObject property = (SoapObject) bodyIn.getProperty(0);
            for (int i = 0; i < property.getPropertyCount(); i++) {
                SoapPrimitive soapPrimitive = (SoapPrimitive) property.getProperty(i);
                System.out.println(soapPrimitive.getValue());
            }
            System.out.println(bodyIn);
        }
    }
}