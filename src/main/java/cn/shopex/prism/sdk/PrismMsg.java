package cn.shopex.prism.sdk;

public class PrismMsg {
  private String client_id;
  private String app_name;
  private long time;
  private int tag;
  private String key;
  private String type;
  private String body;

  public String getClient_id() {
    return client_id;
  }

  public void setClient_id(String client_id) {
    this.client_id = client_id;
  }

  public String getApp_name() {
    return app_name;
  }

  public void setApp_name(String app_name) {
    this.app_name = app_name;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public int getTag() {
    return tag;
  }

  public void setTag(int tag) {
    this.tag = tag;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public String toString() {
    return "PrismMsg{" +
            "client_id='" + client_id + '\'' +
            ", app_name='" + app_name + '\'' +
            ", time=" + time +
            ", tag=" + tag +
            ", key='" + key + '\'' +
            ", type='" + type + '\'' +
            ", body='" + body + '\'' +
            '}';
  }
}
