package cn.shopex.prism.sdk;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StoreTradeAddCase {
    String url = "http://wlqkdsix.apihub1.cn/api";
    String key = "xx";
    String secret = "xx";
    Boolean https_model = true;

    @Test
    public void apiGet() {
        String method = "/platform/notify/status";
        PrismClient prismClient = new PrismClient(url, key, secret, https_model);
        Map<String, String> appParams = new HashMap<String, String>();
        try {
            String apiResult = prismClient.doGet(method, appParams);
            System.out.println(apiResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //testing api post
    @Test
    public void apiPost() {
        String method = "/oms";
        PrismClient prismClient = new PrismClient(url, key, secret, https_model);
        Map<String, String> appParams = new HashMap<String, String>();
        appParams.put("buyer_city", "");
        appParams.put("buyer_memo", "");
        appParams.put("buyer_state", "");
        appParams.put("buyer_uname", "Whatever");
        appParams.put("created", "2019-06-09 13:01:01");
        appParams.put("date", "1562138608");
        appParams.put("format", "json");
        appParams.put("from_node_id", "1838140632");
        appParams.put("to_node_id", "1634120733");
        appParams.put("node_id", "1838140632_1634120733");
        appParams.put("goods_discount_fee", "0");
        appParams.put("has_invoice", "true");
        appParams.put("is_cod", "false");
        appParams.put("is_protect", "0");
        appParams.put("lastmodify", "2019-06-09 13:01:01");
        appParams.put("method", "store.trade.add");
        appParams.put("modified", "2019-06-09 13:01:01");
        appParams.put("orders", "{\"order\":[{\"oid\":\"20171122\",\"type\":\"goods\",\"items_num\":\"1\",\"total_order_fee\":\"8498\",\"status\":\"TRADE_ACTIVE\",\"ship_status\":\"SHIP_NO\",\"pay_status\":\"PAY_FINISH\",\"order_items\":{\"item\":[{\"bn\":\"test\",\"name\":\"测试商品\",\"sku_properties\":\"\",\"price\":\"8498\",\"sale_price\":8498,\"total_item_fee\":\"8498\",\"num\":\"1\",\"item_type\":\"product\",\"item_status\":\"normal\"}]}}]}");
        appParams.put("orders_number", "1");
        appParams.put("pay_status", "PAY_FINISH");
        appParams.put("payed_fee", "8498.00");
        appParams.put("payment_lists", "{\"payment_list\":[{\"payment_id\":\"NO20171122\",\"payment_name\":\"建设银行（分期）\",\"tid\":\"20171122\",\"seller_bank\":\"\",\"seller_account\":\"\",\"pay_fee\":\"8498.00\",\"currency\":\"CNY\",\"currency_fee\":\"8498.00\",\"pay_type\":\"online\",\"pay_time\":null,\"status\":\"SUCC\"}]}");
        appParams.put("promotion_details", "[{\"promotion_name\":\"优惠券\",\"promotion_fee\":\"0.00\"}]");
        appParams.put("receiver_address", "桂林路396号");
        appParams.put("receiver_city", "上海市");
        appParams.put("receiver_district", "徐家汇");
        appParams.put("receiver_mobile", "138XXXXXXXX");
        appParams.put("receiver_name", "陈X");
        appParams.put("receiver_phone", "138XXXXXXXX");
        appParams.put("receiver_state", "上海");
        appParams.put("receiver_zip", "");
        appParams.put("ship_status", "SHIP_NO");
        appParams.put("shipping_fee", "0.00");
        appParams.put("shipping_type", "快递");
        appParams.put("status", "TRADE_ACTIVE");
        appParams.put("tid", "20171122");
        appParams.put("timestamp", "1562138608");
        appParams.put("title", "测试商品");

        appParams.put("total_goods_fee", "8498.00");
        appParams.put("total_trade_fee", "8498.00");
        appParams.put("v", "1.0");

        try {
            String apiResult = prismClient.doPost(method, appParams);
            System.out.println(apiResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void main(String[] args) {
        apiPost();
    }


}
