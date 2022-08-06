package kg.megacom.smsmessage.service;

import kg.megacom.smsmessage.model.Subscriber;
import kg.megacom.smsmessage.service.impl.SubscriberServiceImpl;

public interface SubscriberService {

    SubscriberService INSTANCE = new SubscriberServiceImpl();

    Subscriber findSubscriberByPhone(String phone);

    Subscriber findSubscriberById(int id);

    boolean deactivateSubscriber(String phone);
}
