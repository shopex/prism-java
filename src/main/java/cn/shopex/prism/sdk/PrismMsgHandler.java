package cn.shopex.prism.sdk;

import jp.a840.websocket.WebSocket;
import jp.a840.websocket.exception.WebSocketException;

public interface PrismMsgHandler {
  public void onOpen(WebSocket socket);

  public void onMessage(WebSocket socket, PrismMsg prismMsg);

  public void onError(WebSocket socket, WebSocketException e);

  public void onClose(WebSocket socket);
}
