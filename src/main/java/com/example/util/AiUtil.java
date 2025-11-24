package com.example.util;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

/**
 * @ClassName: AiUtil
 * @Description: 同B站xwxz第三节视频——AiUtils
 * @author: 罗一帆
 * @date: 2024/3/3  23:31
 */
public class AiUtil {
        public static String word(MultipartFile file) throws IOException {
                //设置APPID/AK/SK
                String APP_ID = "59412730";
                String API_KEY = "XfYq6rRWQskPrOe9ftas8xqr";
                String SECRET_KEY = "2EowqaenR0lk2Jg9mKaqsbzdiQPNFN8P";
                // 初始化一个AipOcr
                AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
                // 调用接口
                JSONObject res = client.basicGeneral(file.getBytes(), new HashMap<String, String>());
                System.out.println(res.toString(2));
                return res.toString(2);
        }

}
