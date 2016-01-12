package com.daoc.charplan.util;

import com.daoc.charplan.CharplanApp;

/**
 * Util used for logging.
 */
public class Log {

    /**
     * The depth of the stack.
     */
    private static final int STACK_DEPTH = 4;

    /**
     * The tag to use.
     */
    private static final String LOG_TAG = CharplanApp.LOG_TAG;

    /**
     * {@link StringBuilder} to append the log on.
     */
    private final StringBuilder mStringBuilder;

    /**
     * Constructor of the LogUtil.
     */
    private Log() {
        final StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        final StackTraceElement stackTraceElement = stackTraceElements[STACK_DEPTH];
        mStringBuilder = new StringBuilder(stackTraceElement.getClassName()
                .substring(stackTraceElement.getClassName().lastIndexOf('.') + 1));
        mStringBuilder.append(": ");
        mStringBuilder.append(stackTraceElement.getMethodName());
        mStringBuilder.append('(').append(stackTraceElement.getLineNumber()).append("): ");
    }

    /**
     * Send a {@link android.util.Log#DEBUG} log message.
     *
     * @param msg The message you would like logged.
     */
    public static void d(String msg) {
        new Log().add(msg).printAsDebug();
    }

    /**
     * Send a {@link android.util.Log#WARN} log message.
     *
     * @param msg The message you would like logged.
     */
    public static void w(String msg) {
        new Log().add(msg).printAsWarning();
    }

    /**
     * Send a {@link android.util.Log#ERROR} log message.
     *
     * @param msg The message you would like logged.
     */
    public static void e(String msg) {
        new Log().add(msg).printAsError();
    }

    /**
     * Adds the specified string to the builder.
     *
     * @param msg The message to add.
     * @return A {@link Log} used for chaining.
     */
    private Log add(String msg) {
        mStringBuilder.append(msg);
        return this;
    }

    /**
     * Prints the information in the builder using the  {@link android.util.Log#DEBUG} flag.
     */
    private void printAsDebug() {
        android.util.Log.d(LOG_TAG, mStringBuilder.toString());
    }

    /**
     * Prints the information in the builder using the  {@link android.util.Log#WARN} flag.
     */
    private void printAsWarning() {
        android.util.Log.w(LOG_TAG, mStringBuilder.toString());
    }

    /**
     * Prints the information in the builder using the {@link android.util.Log#ERROR} flag.
     */
    private void printAsError() {
        android.util.Log.e(LOG_TAG, mStringBuilder.toString());
    }
}