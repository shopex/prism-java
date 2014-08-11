package cn.shopex.prism.sdk.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public final class SignTools {
  public static final String CHARSET = "utf-8";

  /**
   * 使用MD5加密
   * @param data 加密前字符串
   * @return
   * @throws java.io.IOException
   */
  public static byte[] encryptMD5(String data) throws IOException {
    byte[] bytes = null;
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      bytes = md.digest(data.getBytes("utf-8"));
    } catch (GeneralSecurityException gse) {
      String msg = getStringFromException(gse);
      throw new IOException(msg);
    }
    return bytes;
  }

  /**
   * 把二进制数据转化为十六进制
   * @param bytes
   * @param isToUpper 是否大写
   * @return
   */
  public static String byte2hex(byte[] bytes, boolean isToUpper) {
    StringBuilder sign = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      String hex = Integer.toHexString(bytes[i] & 0xFF);
      if (hex.length() == 1) {
        sign.append("0");
      }
      if (isToUpper)
        sign.append(hex.toUpperCase());
      else
        sign.append(hex);
    }
    return sign.toString();
  }


  /**
   * <p>根据参数名称将你的所有请求参数按照字母先后顺序排序:key + value .... key + value</p>
   * <p>对除签名和图片外的所有请求参数按key做的升序排列, value无需编码。
   * 例如：将foo=1,bar=2,baz=3 排序为bar=2,baz=3,foo=1
   * 参数名和参数值链接后，得到拼装字符串bar2baz3foo1</p>
   * @param params 请求参数
   * @return 拼装字符串
   */
  public static String mixRequestParams(Map<String, String> params) {
    if (params == null || params.size() == 0) return "";
    Map<String, String> sortedParams = new TreeMap<String, String>(params);
    Set<Map.Entry<String, String>> paramSet = sortedParams.entrySet();
    StringBuilder query = new StringBuilder();
    for (Map.Entry<String, String> param : paramSet) {
      if (!isBlank(param.getKey()) && !"sign".equals(param.getKey())) {
        query.append(param.getKey()).append("=").append(param.getValue());
      }
      query.append("&");
    }
    return query.substring(0, query.length() - 1);
  }
  
  public static String mixHeaderParams(Map<String, String> headers){
    if (headers == null || headers.size() == 0) return "";
    Map<String, String> sortedParams = new TreeMap<String, String>(headers);
    StringBuilder query = new StringBuilder();
    for (Map.Entry<String,String> entry : sortedParams.entrySet()) {
      String key = entry.getKey();
      if (key.equals("Authorization") || key.startsWith("X-Api-")) {
        query.append(key).append("=").append(entry.getValue()).append("&");
      }
    }
    if (query.length() > 0) {
      return query.substring(0,query.length()-1);
    }
    return "";
  }


  /**
   * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
   * <p/>
   * <pre>
   * StringUtil.isBlank(null)      = true
   * StringUtil.isBlank("")        = true
   * StringUtil.isBlank(" ")       = true
   * StringUtil.isBlank("bob")     = false
   * StringUtil.isBlank("  bob  ") = false
   * </pre>
   * @param str 要检查的字符串
   * @return 如果为空白, 则返回<code>true</code>
   */
  public static boolean isBlank(final String str) {
    int length;

    if (str == null || (length = str.length()) == 0) {
      return true;
    }

    for (int i = 0; i < length; i++) {
      if (!Character.isWhitespace(str.charAt(i))) {
        return false;
      }
    }

    return true;
  }

  private static String getStringFromException(Throwable e) {
    String result = "";
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(bos);
    e.printStackTrace(ps);
    try {
      result = bos.toString("utf-8");
    } catch (UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }
    return result;
  }

  public static void main(String[] args) throws Exception {
  }
}
