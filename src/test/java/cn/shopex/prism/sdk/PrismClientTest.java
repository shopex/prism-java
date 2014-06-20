package cn.shopex.prism.sdk;

import jp.a840.websocket.WebSocket;
import jp.a840.websocket.exception.WebSocketException;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 * User: yangyong
 * Date: 14-6-11 下午1:26
 */
public class PrismClientTest {
  String url = "http://dilbmtcv.apihub.cn/api";
  String key = "buwb2lii";
  String secret = "ucr72ygfutspqeuu6s36";

  //测试api get请求
  @Test
  public void apiGet(){
    String method = "/platform/notify/status";
    PrismClient prismClient = new PrismClient(url,key,secret);
    Map<String,String> appParams = new HashMap<String, String>();
    try {
      String apiResult = prismClient.doGet(method,appParams);
      System.out.println(apiResult);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //测试api post请求
  @Test
  public void apiPost(){
    String method = "/platform/notify/write";
    PrismClient prismClient = new PrismClient(url,key,secret);
    Map<String,String> appParams = new HashMap<String, String>();
    appParams.put("data","你好");
    try {
      String apiResult = prismClient.doPost(method, appParams);
      System.out.println(apiResult);
    } catch (IOException e) {
      e.printStackTrace();
    }
  } 

  //测试websocket连接
  @Test
  public void websocketConnect(){
    String method = "/platform/notify";
    final PrismClient prismClient = new PrismClient(url,key,secret);
    prismClient.setPrismMsgHandler(new PrismMsgHandler() {
      @Override
      public void onOpen(WebSocket socket) {
        System.out.println("---> open");
      }
      @Override
      public void onMessage(WebSocket socket, PrismMsg prismMsg) {
        System.out.println("---> receive msg:"+prismMsg);
        if (prismMsg.getTag() == 1) {//这里只对第一条消息做ACK应答
          try {
            socket.send(prismClient.assembleAckData(prismMsg.getTag()));
            System.out.println("发送ACK完成："+prismMsg.getTag());
          } catch (WebSocketException e) {
            e.printStackTrace();
          }
        }
      }
      @Override
      public void onError(WebSocket socket, WebSocketException e) {
        e.printStackTrace();
        System.out.println("---> error:"+e);
      }
      @Override
      public void onClose(WebSocket socket) {
        System.out.println("---> close");
      }
    });
    prismClient.executeNotify(method);
    prismClient.consume();
    try {
      prismClient.publish("order.new","mytest00001");
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
