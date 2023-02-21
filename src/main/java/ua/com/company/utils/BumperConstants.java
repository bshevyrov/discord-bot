package ua.com.company.utils;

public class BumperConstants {
    public static final String PRIVATE_MESSAGE = "TIME TO BUMP!!!";
//   public static final long DELAY_BEFORE_DELETE_MESSAGE =30*1000;//4min 30 sec
   public static final long DELAY_BEFORE_DELETE_MESSAGE = Long.parseLong(PropertiesReader.getDeleteDelay());//4min 30 sec
//   public static final long DELAY_BEFORE_SEND_ANOTHER_MESSAGE = 30*1000;//5min
   public static final long DELAY_BEFORE_SEND_ANOTHER_MESSAGE = Long.parseLong(PropertiesReader.getSendDelay());//5min
//    public static final long PAUSE_BETWEEN_NEW_TASK = 4*60*60*1000L;
    public static final long PAUSE_BETWEEN_MANUAL_NEW_TASK = Long.parseLong(PropertiesReader.getManualRoundDelay());//MINUTES
    public static final long PAUSE_BETWEEN_AUTO_NEW_TASK = Long.parseLong(PropertiesReader.getAutoRoundDelay());//MINUTES

}
