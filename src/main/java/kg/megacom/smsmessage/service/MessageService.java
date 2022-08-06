package kg.megacom.smsmessage.service;

import kg.megacom.smsmessage.model.MessageInfo;
import kg.megacom.smsmessage.model.Subscriber;
import kg.megacom.smsmessage.service.impl.MessageServiceImpl;

import java.util.List;

public interface MessageService {

    MessageService INSTANCE = new MessageServiceImpl();

    boolean sendMessage(String messageText, String recPhone, String senPhone);

    boolean saveMessages(String messageText, Subscriber sender, Subscriber recipient);

    List<MessageInfo> showListsByPhone(String phone);

}
