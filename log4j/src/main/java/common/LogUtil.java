package common;

import org.apache.logging.log4j.Logger;

public class LogUtil {

    /**
     * エラーメッセージによって、ログレベルを設定する
     * @param logger  ロガー
     * @param message ログメッセージ
     */
    public static void outLog(Logger logger, String message) {
//        System.out.println(getLogInfo(logger));     // ConsoleAppender用

        // message が "error" だったらエラーログを出力
        switch (message) {
            case "error" -> { logger.error(message); }
            default      -> { logger.debug(message); }
        }
    }

    /**
     * Exception名とエラーメッセージを出力
     * @param logger ロガー
     * @param e      Exception
     */
    public static void outExceptionLog(Logger logger, Throwable e) {
//        System.out.println(getLogInfo(logger));     // ConsoleAppender用
        logger.error(e);
    }

    /**
     * スタックトレースとエラーメッセージを出力する
     * @param logger ロガー
     * @param msg    エラーメッセージ
     * @param e      Exception
     */
    public static void outExceptionLog(Logger logger, String msg, Throwable e) {
//        System.out.println(logger.getName());     // ConsoleAppender用
        logger.error(msg, e);
    }

    /**
     * Logger名とレベルを文字列で返却する(確認用）
     * @param logger  ロガー
     * @return Logger 文字列に変換したロガー情報
     */
    private static String getLogInfo(Logger logger) {
        StringBuilder bs = new StringBuilder();
        bs.append(logger.getLevel().name());
        bs.append(" : ");
        bs.append(logger.getName());
        return String.valueOf(bs);
    }
}
