package codingwithme.java.telegram.bot.utils;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

public class Messenger {

    public static void send(TelegramBot bot, Message message, String text) {
        SendMessage sendMessage = new SendMessage(message.chat().id(), text);
        send(bot, sendMessage);
    }

    public static void sendHtml(TelegramBot bot, Message message, String html) {
        SendMessage sendMessage = new SendMessage(message.chat().id(), html);
        sendMessage.parseMode(ParseMode.HTML);
        send(bot, sendMessage);
    }

    public static void reply(TelegramBot bot, Message message, String text) {
        SendMessage sendMessage = new SendMessage(message.chat().id(), text);
        sendMessage.replyToMessageId(message.messageId());
        send(bot, sendMessage);
    }

    public static void replyHtml(TelegramBot bot, Message message, String html) {
        SendMessage sendMessage = new SendMessage(message.chat().id(), html);
        sendMessage.parseMode(ParseMode.HTML);
        sendMessage.replyToMessageId(message.messageId());
        send(bot, sendMessage);
    }

    private static void send(TelegramBot bot, SendMessage message) {
        SendResponse response = bot.execute(message);
        System.out.println("Response sent: " + response.isOk());
    }

}
