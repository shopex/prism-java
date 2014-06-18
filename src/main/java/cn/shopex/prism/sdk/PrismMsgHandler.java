package cn.shopex.prism.sdk;

import jp.a840.websocket.WebSocket;
import jp.a840.websocket.exception.WebSocketException;

/**
 * Created with IntelliJ IDEA.
 * User: yangyong
 * Date: 14-6-17 上午10:42
 */
public interface PrismMsgHandler {
  public void onOpen(WebSocket socket);

  public void onMessage(WebSocket socket, PrismMsg prismMsg);

  public void onError(WebSocket socket, WebSocketException e);

  public void onClose(WebSocket socket);
}
