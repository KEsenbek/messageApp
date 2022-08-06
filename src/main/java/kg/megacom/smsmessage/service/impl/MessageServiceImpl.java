package kg.megacom.smsmessage.service.impl;

import kg.megacom.smsmessage.dao.MessageDB;
import kg.megacom.smsmessage.exception.SubscriberBlockedException;
import kg.megacom.smsmessage.exception.SubscriberLimitException;
import kg.megacom.smsmessage.exception.SubscriberNotFoundException;
import kg.megacom.smsmessage.model.Message;
import kg.megacom.smsmessage.model.MessageInfo;
import kg.megacom.smsmessage.model.Subscriber;
import kg.megacom.smsmessage.service.MessageService;
import kg.megacom.smsmessage.service.SubscriberService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl implements MessageService {

    @Override
    public boolean sendMessage(String messageText, String recPhone, String senPhone) {
        Subscriber recipient = SubscriberService.INSTANCE.findSubscriberByPhone(recPhone);
        Subscriber sender = SubscriberService.INSTANCE.findSubscriberByPhone(senPhone);
        if (recipient == null || sender == null) {
            throw new SubscriberNotFoundException("Пользователь не найден!");
        }
        if (recipient.isBlocked()) {
            throw new SubscriberBlockedException("Получатель заблокирован!");
        }
        List<Message> messageList = MessageDB.INSTANCE.getMessagesBySenderId(sender.getId());
        boolean isResultValidation = validationData(messageList, recipient);
        if (isResultValidation) {
            return saveMessages(messageText, sender, recipient);
        }
        return false;
    }

    @Override
    public boolean saveMessages(String messageText, Subscriber sender, Subscriber recipient) {
        return MessageDB.INSTANCE.saveMessagesIntoDb(messageText, sender, recipient);
    }

    @Override
    public List<MessageInfo> showListsByPhone(String phone) {
        List<Message> messageList = MessageDB.INSTANCE.getListMessagesByPhone(phone);
        List<MessageInfo> messageInfos = new ArrayList<>();
        for (Message item: messageList) {
            MessageInfo itemInfo = new MessageInfo(
                    item.getMsgText(),
                    item.getMsgDate(),
                    item.getSender().getPhone(),
                    item.getSender().getId()
            );

            messageInfos.add(itemInfo);
        }
        return messageInfos;
    }

    private boolean validationData(List<Message> messageList, Subscriber recipient) {
        LocalDate localDateCurrent = LocalDate.now();
        LocalDateTime startOfDay = localDateCurrent.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusMinutes(1);
        int globalCounter = 0, counterForCheckRecipient = 0;
        for (Message item: messageList) {
            if (item.getMsgDate().isAfter(startOfDay) && item.getMsgDate().isBefore(endOfDay)) {
                globalCounter++;
                if (item.getRecipient().getId() == recipient.getId()) counterForCheckRecipient++;
            }
        }
        if (globalCounter >= 6) {
            throw new SubscriberLimitException("Вы исчерпали лимит за 24 часа");
        } else if (counterForCheckRecipient >= 2) {
            throw new SubscriberLimitException("Вы уже отправляли сообщение данному пользователю два раза за этот день");
        }
        return true;
    }

}


/*
        MessageServiceImpl -> метод для отправки
        subscriberFromDb.getBlocked()
            select subscriberFromDb.getId() проверить:
                1. В день можно отправить только 6 сообщений -> Вы исчерпали лимит за 24 часа
                2. Нельзя отправлять больше двух раз одному и тому же абононету за 24 часа -> Вы уже отправляли сообщение данному пользователю два раза
                Сделать сохранения сообщения
        Alert об ошшибке
* */
