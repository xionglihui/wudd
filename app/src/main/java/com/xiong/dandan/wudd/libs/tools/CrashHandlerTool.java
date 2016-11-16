package com.xiong.dandan.wudd.libs.tools;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class CrashHandlerTool implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandlerTool---->";
    private Thread.UncaughtExceptionHandler mDefaultUEH;

    //构造函数，获取默认的处理方法
    public CrashHandlerTool() {
        this.mDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
    }

    //这个接口必须重写，用来处理我们的异常信息
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        //获取跟踪的栈信息，除了系统栈信息，还把手机型号、系统版本、编译版本的唯一标示
        StackTraceElement[] trace = ex.getStackTrace();
        StackTraceElement[] trace2 = new StackTraceElement[trace.length + 3];
        System.arraycopy(trace, 0, trace2, 0, trace.length);
        trace2[trace.length + 0] = new StackTraceElement("Android", "MODEL", android.os.Build.MODEL, -1);
        trace2[trace.length + 1] = new StackTraceElement("Android", "VERSION", android.os.Build.VERSION.RELEASE, -1);
        trace2[trace.length + 2] = new StackTraceElement("Android", "FINGERPRINT", android.os.Build.FINGERPRINT, -1);
        //追加信息，因为后面会回调默认的处理方法
        ex.setStackTrace(trace2);
        ex.printStackTrace(printWriter);
        //把上面获取的堆栈信息转为字符串，打印出来
        String stacktrace = result.toString();
        printWriter.close();
        SystemLog.E(TAG, stacktrace); // 写入sd卡
        mDefaultUEH.uncaughtException(thread, ex);
    }
}