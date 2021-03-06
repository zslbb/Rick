package com.absurd.rick.event.support.guava;
import com.absurd.rick.event.support.Event;
import com.absurd.rick.event.EventSyncExtra;
import com.absurd.rick.event.Handler;
import com.absurd.rick.util.SpringContextUtil;
import com.google.common.eventbus.Subscribe;

import java.util.Collection;
import java.util.Map;

/**
 * Created by wangwenwei on 17/8/3.
 */
public class EventHandlerProxy implements Handler {

    private Handler handler;

    public EventHandlerProxy(Handler handler) {
        this.handler = handler;
    }

    @Subscribe
    @Override
    public void handler(Event event) {
        resolverExtra(event);
        handler.handler(event);
    }

    private void resolverExtra(Event event) {
        Map<String,Object> map =  event.getExtraData();
        Collection<Object> eventSyncs = SpringContextUtil.getBeanByTypeWithCache(EventSyncExtra.class);
        for(Object eventSync : eventSyncs){
            if (eventSync instanceof EventSyncExtra){
                EventSyncExtra eventSyncExtra =  (EventSyncExtra) eventSync;
                eventSyncExtra.setExtra(map.get(eventSync.getClass().getName()));
            }
        }
    }
}
