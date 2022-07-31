package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import common.LogUtil;

/**
 * ログを出力するクラス
 */
public class ExeLog4j {

    // ロガー
    private static final Logger LOGGER = LogManager.getLogger(ExeLog4j.class);

    public static void main(String[] args) {
        exe();
    }

    private static void exe() {
        LogUtil.outLog(LOGGER, "debug");
        System.out.println("\n------------------------------------------\n");
        LogUtil.outLog(LOGGER, "error");
    }
}
