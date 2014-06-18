package cn.shopex.prism.sdk.springmvc.main;


/**
 * console启动主程序
 * 
 * @author ivan
 * 
 */
public class ConsoleMain {

	public static void main(String[] args) {
    try{
      JettyServer server = new JettyServer();
      server.start();
    }catch (Exception e){
      e.printStackTrace();
    }
	}

}
