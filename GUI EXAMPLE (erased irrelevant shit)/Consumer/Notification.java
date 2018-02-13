/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.PL.Consumer;

import Server.BL.Consumer;
import Server.BL.Coupon;
import Server.BL.IconsumerController;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

/**
 *
 * @author Guy
 */
public class Notification {
  private static final String DATE_FORMAT_NOW = "mm";
  private static final String DATE_FORMAT_NOW2 = "yyyy-MM-dd'T'HH:mm";
  private int lastTime=now() ;
  private IconsumerController consumerController;
  private Consumer consumer;
    public Notification(IconsumerController consumerController,Consumer consumer){
        this.consumerController=consumerController;
        this.consumer=consumer;
        checkNotifications();
    }
     
  private int now() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
    return Integer.parseInt(sdf.format(cal.getTime()));
  }
  private String now2() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW2);
    return sdf.format(cal.getTime());
  }
  public void checkNotifications(){
      String lastSeen=consumerController.getLastSeen(consumer.getUserName());
      Vector<String> favorite=consumerController.getConsumerFavorite(consumer.getUserName());
      boolean bigerThan60=lastSeenIsBigerThan60(now2(),lastSeen);
      boolean hasNew= consumerController.hasNewCouponFromLastSeen(lastSeen,favorite);
      if(bigerThan60&&hasNew){
          consumerController.setLastSeen(now2());
          new CouponsNotificationWindow(this);
      }
      /*if(Math.abs(now()-lastTime)%60>2){
          lastTime=now();
          new CouponsNotificationWindow(this);
      }
      */
  }
  protected Vector<String> getCities(){
      return consumerController.getAllCities();
  }

    public Vector<Coupon> getAllCouponsInCity(String city) {
        return consumerController.getCouponsOfCity(city);
    }
  /*
  public static final String DATE_FORMAT_NOW = "mm";
  private static int lastTime=now() ;
  private static IconsumerController controller=new ConsumerController();
  private static int now() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
    return Integer.getInteger(sdf.format(cal.getTime())).intValue();
  }
  public static void checkNotifications(){
      if(Math.abs(now()-lastTime)%60>5){
          lastTime=now();
          new CouponsNotificationWindow();
      }
  }
  protected static Vector<String> getCities(){
      return controller.getAllCities();
  }

    public static Vector<Coupon> getAllCouponsInCity(String city) {
        return controller.getCouponsOfCity(city);
    }
    */

    private boolean lastSeenIsBigerThan60(String now2, String lastSeen) {
        String dateyyyy=now2.substring(0,now2.indexOf('-'));
        now2=now2.substring(now2.indexOf('-')+1);
        String datemm=now2.substring(0,now2.indexOf('-'));;
        now2=now2.substring(now2.indexOf('-')+1);
        String datedd=now2.substring(0,now2.indexOf('T'));
        now2=now2.substring(now2.indexOf('T')+1);
        String dateHH=now2.substring(0,now2.indexOf(':'));
        now2=now2.substring(now2.indexOf(':')+1);
        String dateMM=now2;
        
        String dateyyyy2=lastSeen.substring(0,lastSeen.indexOf('-'));
        now2=now2.substring(lastSeen.indexOf('-')+1);
        String datemm2=lastSeen.substring(0,lastSeen.indexOf('-'));;
        now2=now2.substring(lastSeen.indexOf('-')+1);
        String datedd2=lastSeen.substring(0,lastSeen.indexOf('T'));
        now2=now2.substring(lastSeen.indexOf('T')+1);
        String dateHH2=lastSeen.substring(0,lastSeen.indexOf(':'));
        now2=now2.substring(lastSeen.indexOf(':')+1);
        String dateMM2=lastSeen;
        
        boolean year=Integer.parseInt(dateyyyy)>Integer.parseInt(dateyyyy2);
        boolean month=Integer.parseInt(datemm)>Integer.parseInt(datemm2);
        boolean day=Integer.parseInt(datedd)>Integer.parseInt(datedd2);
        boolean today=Integer.parseInt(dateHH)*60+Integer.parseInt(dateMM)>Integer.parseInt(dateHH2)*60+Integer.parseInt(dateMM)+60;
        
        if(year)
            return true;
        if(Integer.parseInt(dateyyyy)==Integer.parseInt(dateyyyy2)){
            if(month)
                return true;
            if(Integer.parseInt(datemm)==Integer.parseInt(datemm2)){
                if(day)
                    return true;
                if(Integer.parseInt(datedd)==Integer.parseInt(datedd2)){
                    if(today)
                        return true;
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }
}
