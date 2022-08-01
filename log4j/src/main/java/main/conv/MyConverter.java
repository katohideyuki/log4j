package main.conv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.util.PerformanceSensitive;

import common.LogUtil;

@Plugin(name = "MyConverter", category = "Converter")
@ConverterKeys({ "conv" })
@PerformanceSensitive
public class MyConverter extends LogEventPatternConverter {

    // コンバート失敗時に使用するロガー
    private static final Logger LOGGER = LogManager.getLogger(MyConverter.class);

    /**
     * コンストラクタ
     * @param options
     */
    public MyConverter(String name, String style) {
        super(name, style);
    }

    /**
     * パターンコンバーターのインスタンスを取得
     * @param options レベル名のリストと、レベルに表示される値を含む場合がある(null許容)
     * @return        パターンコンバーターのインスタンス
     */
    public static MyConverter newInstance(final String[] options) {
        return new MyConverter("conv", Thread.currentThread().getName());
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        String msg = event.getMessage().getFormattedMessage();

        try {
            // メッセージがエラーだった場合、例外をスロー
            if (msg.equals("error"))
                throw new Exception();

         // 受け取ったメッセージを大文字に変換
            toAppendTo.append(msg.toUpperCase());
        } catch (Exception e) {
            LogUtil.outExceptionLog(LOGGER, "convertに失敗しました", e);
        }
    }
}

/**
 * ※1 Converter内でExceptionを発生させた場合
 *      - 例外が発生してもログ出力処理は継続して行われるため、ログを出力しない。なんてことはできない。
 *        - 結果、メッセージ以外の情報がログに出力される。
 *          - 2022/07/31 15:26:06.951 [main] [CONSOLE] ERROR -     <- メッセージが空になっている。
 *          - 最初にLoggerに定義したロガーのみ、コンバート失敗の空ログが出力される。
 *
 *      - catch構文でエラー用のログを追加で出力するときの注意点
 */