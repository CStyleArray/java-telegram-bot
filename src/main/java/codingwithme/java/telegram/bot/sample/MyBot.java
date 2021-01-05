package codingwithme.java.telegram.bot.sample;

import codingwithme.java.telegram.bot.base_handlers.LongPollingHandler;
import com.pengrad.telegrambot.model.*;

public class MyBot extends LongPollingHandler {

    public MyBot(String token) {
        super(token);
    }

    @Override
    protected void onStart(int id, Message message) {
        System.out.println("Bot started by " + message.from().username());
    }

    @Override
    protected void onTextMessage(int id, Message message) {
        System.out.println(message.text());
    }

    @Override
    protected void onEdit(int id, Message message) {

    }

    @Override
    protected void onChannelPost(int id, Message message) {

    }

    @Override
    protected void onChannelPostEdit(int id, Message message) {

    }

    @Override
    protected void onCallbackQuery(int id, CallbackQuery query) {

    }

    @Override
    protected void onInlineQuery(int id, InlineQuery query) {

    }

    @Override
    protected void onInlineResultSelect(int id, InlineQuery query, ChosenInlineResult chosenInlineResult) {

    }

    @Override
    protected void onPoll(int id, Poll poll) {

    }

    @Override
    protected void onPollAnswer(int id, PollAnswer answer) {

    }

    @Override
    protected void onShippingQuery(int id, ShippingQuery shippingQuery) {

    }

    @Override
    protected void onPreCheckoutQuery(int id, PreCheckoutQuery preCheckoutQuery) {

    }

    @Override
    protected void onJoin(int id, Chat chat, User user) {
        System.out.println(user.username() + " added to " + chat.title());
    }

    @Override
    protected void onLeave(int id, Chat chat, User user) {

    }

    @Override
    protected void onReply(int id, Chat chat, Message message, Message repliedToMessage) {

    }

    @Override
    protected void onUnknownUpdate(Update update) {

    }
}
