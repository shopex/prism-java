package cn.shopex.prism.sdk;

import com.alibaba.fastjson.JSONObject;
import jp.a840.websocket.WebSocket;
import jp.a840.websocket.exception.WebSocketException;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class PrismClientTest {

    String url = "http://domaiurl/api";
    String key = "your prism key";
    String secret = "your prism secret";

    Boolean https_model = true;

    //testing api get
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
        String method = "/platform/notify/write";
        PrismClient prismClient = new PrismClient(url, key, secret, https_model);
        Map<String, String> appParams = new HashMap<String, String>();
        appParams.put("data", "hello");
        try {
            String apiResult = prismClient.doPost(method, appParams);
            System.out.println(apiResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //testing api post
    @Test
    public void apiMethod() {
        String method = "/oms";
        PrismClient prismClient = new PrismClient(url, key, secret, https_model);

        Map<String, String> appParams = JSONObject.parseObject("{\"receiver_city\":\"杭州\",\"orders_number\":\"2\",\"shipping_type\":\"快递\",\"is_cod\":\"false\",\"tid\":\"709274618384220001\",\"receiver_state\":\"浙江\",\"pay_status\":\"PAY_FINISH\",\"payed_fee\":\"0.00\",\"total_trade_fee\":\"0.00\",\"modified\":\"2018-07-09 17:36:19\",\"receiver_name\":\"林xx了\",\"method\":\"store.trade.add\",\"created\":\"2018-07-09\",\"receiver_district\":\"西湖区\",\"format\":\"json\",\"has_invoice\":\"false\",\"receiver_mobile\":\"1316217193\",\"ship_status\":\"SHIP_NO\",\"shipping_fee\":\"0.00\",\"v\":\"1.0\",\"receiver_address\":\"xx大厦\",\"orders\":\"{\\\"items_num\\\":4,\\\"oid\\\":\\\"70927461838422C0001\\\",\\\"order_items\\\":{\\\"item\\\":[{\\\"bn\\\":\\\"\\\",\\\"item_status\\\":\\\"normal\\\",\\\"item_type\\\":\\\"product\\\",\\\"num\\\":2,\\\"price\\\":\\\"1\\\",\\\"sale_price\\\":\\\"1\\\",\\\"total_item_fee\\\":\\\"3\\\"},{\\\"bn\\\":\\\"\\\",\\\"item_status\\\":\\\"normal\\\",\\\"item_type\\\":\\\"product\\\",\\\"num\\\":2,\\\"price\\\":\\\"1\\\",\\\"sale_price\\\":\\\"1\\\",\\\"total_item_fee\\\":\\\"3\\\"}]},\\\"refund_status\\\":\\\"PAY_FINISH\\\",\\\"total_order_fee\\\":0.0,\\\"type\\\":\\\"goods\\\"}\",\"is_protect\":\"false\",\"node_id\":\"1092156036_1129944230\",\"status\":\"TRADE_FINISHED\"}", Map.class);

        try {
            String apiResult = prismClient.doPost(method, appParams);
            System.out.println(apiResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //testing websocket connection
    @Test
    public void websocketConnect() {
        String method = "/platform/notify";
        final PrismClient prismClient = new PrismClient(url, key, secret, https_model);
        prismClient.setPrismMsgHandler(new PrismMsgHandler() {
            @Override
            public void onOpen(WebSocket socket) {
                System.out.println("---> open");
            }

            @Override
            public void onMessage(WebSocket socket, PrismMsg prismMsg) {
                System.out.println("---> receive msg:" + prismMsg);
                if (prismMsg.getTag() == 1) {
                    try {
                        socket.send(prismClient.assembleAckData(prismMsg.getTag()));
                        System.out.println("sending ack：" + prismMsg.getTag());
                    } catch (WebSocketException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(WebSocket socket, WebSocketException e) {
                e.printStackTrace();
                System.out.println("---> error:" + e);
            }

            @Override
            public void onClose(WebSocket socket) {
                System.out.println("---> close");
            }
        });
        prismClient.executeNotify(method);
        prismClient.consume();
        try {
            prismClient.publish("order.new", "mytest00001");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (WebSocketException e) {
            e.printStackTrace();
        }
        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
