package com.example.controller;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

/**
 * @ClassName: AiController
 * @Description: Ocr文本识别
 */
@RestController
@CrossOrigin
public class AiController {

    @RequestMapping(value = "/word",method = RequestMethod.POST)
    public StringBuffer word(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        //设置APPID/AK/SK
        String APP_ID = "59412730";
        String API_KEY = "XfYq6rRWQskPrOe9ftas8xqr";
        String SECRET_KEY = "2EowqaenR0lk2Jg9mKaqsbzdiQPNFN8P";
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 调用接口
        JSONObject res = client.basicGeneral(file.getBytes(), new HashMap<String, String>());
        JSONArray jsonArray = res.getJSONArray("words_result");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < jsonArray.length(); i++) {
            sb.append(jsonArray.getJSONObject(i).getString("words"))
                    .append("\n");
        }
        model.addAttribute("words_result", sb.toString());
        System.out.println("pos1");

        return sb;
    }
}
