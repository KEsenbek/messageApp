package kg.megacom.smsmessage.dao;

import kg.megacom.smsmessage.dao.impl.SubscriberDBImpl;
import kg.megacom.smsmessage.model.Subscriber;

public interface SubscriberDB {

    SubscriberDB INSTANCE = new SubscriberDBImpl();

    Subscriber findSubcriberByPhone(String phone);

    Subscriber findSubcriberById(int id);

    boolean deactivateSubscriber(String phone);

}
