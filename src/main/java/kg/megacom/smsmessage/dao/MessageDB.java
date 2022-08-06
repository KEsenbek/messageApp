package kg.megacom.smsmessage.dao;

import kg.megacom.smsmessage.dao.impl.MessageDBImpl;
import kg.megacom.smsmessage.model.Message;
import kg.megacom.smsmessage.model.Subscriber;

import java.util.List;

public interface MessageDB {


    MessageDB INSTANCE = new MessageDBImpl();

    List<Message> getMessagesBySenderId(int senderId);

    boolean saveMessagesIntoDb(String messageText, Subscriber sender, Subscriber recipient);

    List<Message> getListMessagesByPhone(String phone);

}
