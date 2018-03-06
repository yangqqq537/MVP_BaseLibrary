package com.mecby.base.utils.message;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by jerry on 2017/8/25 0025.
 * 事件发送工具
 *
 */

public class EventBusUtil {
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }
}
