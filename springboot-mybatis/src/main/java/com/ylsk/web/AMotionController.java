package com.ylsk.web;

import apache.math.fft.CalcFFT;
import apache.math.fft.TestThread;
import com.ylsk.entry.MotionDataItem;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gsj on 2018/3/17.
 */
@RestController
@RequestMapping("/amotion")
public class AMotionController {

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public List<MotionDataItem> test1() {
        String jsonstr =
                "[\n" +
                "{\"x\":-0.209442138671875,\"y\":-1.2432861328125,\"z\":9.585494995117188,\"timestamp\":1521260728582}," +
                "{\"x\":-0.1753387451171875,\"y\":-1.22772216796875,\"z\":9.70880126953125,\"timestamp\":1521260728587}," +
                "{\"x\":-0.1250457763671875,\"y\":-1.2486724853515625,\"z\":9.81414794921875,\"timestamp\":1521260728592}," +
                "{\"x\":-0.1483917236328125,\"y\":-1.2175445556640625,\"z\":9.84527587890625,\"timestamp\":1521260728602}," +
                "{\"x\":-0.2136383056640625,\"y\":-1.2498626708984375,\"z\":9.657928466796875,\"timestamp\":1521260728606}," +
                "{\"x\":-0.1884918212890625,\"y\":-1.238494873046875,\"z\":9.598663330078125,\"timestamp\":1521260728617}," +
                "]";

        FileInputStream fis;
        try {
            Resource resource = new ClassPathResource("motion3.json");
            File file = resource.getFile();
            fis = new FileInputStream(file);
            int size = fis.available();
            byte[] buf = new byte[size];
            fis.read(buf);
            jsonstr = new String(buf);
            fis.close();
        } catch (Exception e) {
            System.out.println("文件读取异常：" + e.getMessage());
        }

        // 将json数据准换为JSONObject对象
        JSONArray jsonArray;
        jsonArray = JSONArray.fromObject(jsonstr);
        System.out.println("数组大小:" + jsonArray.size());
        List<MotionDataItem> listMotionDatas = new ArrayList<MotionDataItem>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            MotionDataItem item = (MotionDataItem) JSONObject.toBean(obj, MotionDataItem.class);
            item.cacuABS();
            listMotionDatas.add(item);
            System.out.println("index=" + i + ":" + item.toString());
        }

        System.out.println("--------------------------------------------------------------------");
        System.out.println(" 开始分析 .... ");

        int index = 0;
        double[] accValues  = new double[128];
        String linestr = "";
        int thid = 0;
        for(int j = 0; j<listMotionDatas.size(); j++)
        {
            if(index == 128)
            {
                thid++;
                index = 0;
                linestr = "";

                CalcFFT fft = new CalcFFT(accValues);
                TestThread th = new TestThread(fft);
                th.setName("Thread-[" + thid + "]-"+System.currentTimeMillis());
                th.start();
            }
            accValues[index] = listMotionDatas.get(j).getAbsXYZ();
            linestr += accValues[index]+",";

            index++;
        }

        System.out.println(" .... 分析结束 ");
        System.out.println("--------------------------------------------------------------------");

        return jsonArray;
    }
}
