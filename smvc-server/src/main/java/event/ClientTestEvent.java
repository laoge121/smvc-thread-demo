package event;

import org.springframework.context.ApplicationEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pei.xu on 2015/10/14.
 */
public class ClientTestEvent extends ApplicationEvent {

    private Map<String, Object> dataMap = new HashMap<String, Object>();

    public Map<String, Object> getDataMap() {
        return dataMap;
    }


    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public ClientTestEvent(Map<String, Object> source) {
        super(source);
        dataMap.putAll(source);
    }
}
