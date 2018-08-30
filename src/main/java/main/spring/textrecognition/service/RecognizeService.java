package main.spring.textrecognition.service;

import main.spring.textrecognition.auth.AuthService;
import main.spring.textrecognition.pojo.Keys;
import main.spring.textrecognition.util.Base64Util;
import main.spring.textrecognition.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecognizeService {

    @Autowired
    public Keys keys;

    @Autowired
    public AuthService service;

    public String recognize(MultipartFile pics) {
        String ret = "";
        String base64 = null;
        String token = service.getAuth(keys.AK, keys.SK);
        try {
            base64 = Base64Util.byte2Base64(pics.getBytes());
            String url = keys.url + "?access_token=" + token;
            String body = "image=" + URLEncoder.encode(base64, "utf-8");
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            ret = RequestUtil.doPost(url, body, "utf-8", headers);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return ret;
    }

}
