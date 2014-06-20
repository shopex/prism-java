package cn.shopex.prism.sdk.springmvc.main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class JettyServer {

  private static final Logger logger = LoggerFactory.getLogger(JettyServer.class);

  // 根路径
  private String BASE_DIR_PATH = System.getProperty("Dbase.dir");

  // jetty服务器
  private Server server;

  private WebAppContext context;

  // jetty服务状态，默认为未启动
  private AtomicBoolean isStart = new AtomicBoolean(false);

  public JettyServer() {
    try {
      server = new Server();
      server.setHandler(new DefaultHandler());
      XmlConfiguration cfg = null;
      // jetty配置文件路径
      String jettyXMLPath = genFilePath(BASE_DIR_PATH, "src/test/resources/webapp/jetty.xml");
      System.out.println(jettyXMLPath);
      cfg = new XmlConfiguration(new FileInputStream(jettyXMLPath));
      cfg.configure(server);

      String resouceBase = genFilePath(BASE_DIR_PATH, "src/test/resources/webapp");
      context = new WebAppContext();
      context.setResourceBase(resouceBase);
      context.setContextPath("/");
      context.setParentLoaderPriority(true);
      HandlerList handlers = new HandlerList();
      handlers.setHandlers(new Handler[]{context});
      server.setHandler(handlers);
    } catch (Exception e) {
      logger.error("Jetty Server exception:", e);
    }
  }

  public static String genFilePath(String dir, String subFile) {
//    if (StringUtils.isEmpty(dir) || StringUtils.isEmpty(subFile)) {
//      return null;
//    }
    File f = new File(dir, subFile);
    return f.getAbsolutePath();
  }

  /**
   * 启动Jetty服务器
   */
  public void start() {
    try {
      if (isStart.get()) {
        return;
      }
      server.start();
      isStart.set(true);
      logger.info("Jetty Server has been started...");
    } catch (Exception e) {
      logger.error("Jetty Server is not been started,because : ", e);
    }
  }

  public void stop() {
    try {
      if (isStart.get()) {
        server.stop();
        isStart.set(false);
        logger.info("Jetty Server has been stopped...");
      }
    } catch (Exception e) {
      logger.error("Jetty Server is not been stopped,because : ", e);
    }
  }

}
