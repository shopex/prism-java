package cn.shopex.prism.sdk;

import cn.shopex.prism.sdk.util.SignTools;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ShopexMatrixCase {

    private static String _token_str = "xxx";

    public static String mixShopexMatrixRequestParams(Map<String, String> params) {
        if (params == null || params.size() == 0) return "";
        Map<String, String> sortedParams = new TreeMap<String, String>(params);
        Set<Map.Entry<String, String>> paramSet = sortedParams.entrySet();
        StringBuilder query = new StringBuilder();
        for (Map.Entry<String, String> param : paramSet) {
            if (!"sign".equals(param.getKey())) {
                query.append(param.getKey()).append(param.getValue());
            }
        }
        return query.toString();
    }

    public static String sign(String sign_str, String token) {
        String sign = "";
        try {
            String first_md5 = SignTools.byte2hex(SignTools.encryptMD5(sign_str), true);
            sign = SignTools.byte2hex(SignTools.encryptMD5(first_md5 + token), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sign;
    }


    public static void main(String[] args) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("logi_code", "SF");
        params.put("from_api_v", "2.2");
        params.put("app_id", "public_b2c");
        params.put("logi_name", "顺丰特惠-山东");
        params.put("delivery_bn", "2102022000638");
        params.put("node_id", "1094116235");
        params.put("date", "2021-02-02+09:41:38");
        params.put("task", "161223009809371981680070");
        params.put("logi_no", "031190641772");
        params.put("order_bn", "21A31210910010026761");
        params.put("method", "b2c.delivery.update");

        String sorted_str = mixShopexMatrixRequestParams(params);
        String sign = sign(sorted_str, _token_str);

        System.out.println(sign);
    }
}
