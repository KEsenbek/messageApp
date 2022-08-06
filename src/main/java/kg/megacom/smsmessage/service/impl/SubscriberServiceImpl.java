package kg.megacom.smsmessage.service.impl;

import kg.megacom.smsmessage.dao.SubscriberDB;
import kg.megacom.smsmessage.model.Subscriber;
import kg.megacom.smsmessage.service.SubscriberService;

public class SubscriberServiceImpl implements SubscriberService {


    @Override
    public Subscriber findSubscriberByPhone(String phone) {
        Subscriber subscriberFromDb = SubscriberDB.INSTANCE.findSubcriberByPhone(phone);
        return subscriberFromDb;
    }

    @Override
    public Subscriber findSubscriberById(int id) {
        return SubscriberDB.INSTANCE.findSubcriberById(id);
    }

    @Override
    public boolean deactivateSubscriber(String phone) {
        return SubscriberDB.INSTANCE.deactivateSubscriber(phone);
    }
}
