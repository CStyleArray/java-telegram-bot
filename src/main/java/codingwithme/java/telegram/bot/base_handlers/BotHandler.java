package codingwithme.java.telegram.bot.base_handlers;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.response.BaseResponse;

public abstract class BotHandler {

    final TelegramBot bot;

    public BotHandler(String token) {
        bot = new TelegramBot(token);
    }

    protected abstract void onStart(int id, Message message);
    protected abstract void onTextMessage(int id, Message message);
    protected abstract void onEdit(int id, Message message);
    protected abstract void onChannelPost(int id, Message message);
    protected abstract void onChannelPostEdit(int id, Message message);
    protected abstract void onCallbackQuery(int id, CallbackQuery query);
    protected abstract void onInlineQuery(int id, InlineQuery query);
    protected abstract void onInlineResultSelect(int id, InlineQuery query, ChosenInlineResult chosenInlineResult);
    protected abstract void onPoll(int id, Poll poll);
    protected abstract void onPollAnswer(int id, PollAnswer answer);
    protected abstract void onShippingQuery(int id, ShippingQuery shippingQuery);
    protected abstract void onPreCheckoutQuery(int id, PreCheckoutQuery preCheckoutQuery);
    protected abstract void onJoin(int id, Chat chat, User user);
    protected abstract void onLeave(int id, Chat chat, User user);
    protected abstract void onReply(int id, Chat chat, Message message, Message repliedToMessage);
    protected abstract void onUnknownUpdate(Update update);

    public TelegramBot getBot() {
        return bot;
    }

    public <T extends BaseRequest<T, R>, R extends BaseResponse> R execute(BaseRequest<T, R> request) {
        return bot.execute(request);
    }

    public <T extends BaseRequest<T, R>, R extends BaseResponse> void execute(T request, Callback<T, R> callback) {
        bot.execute(request, callback);
    }
     protected void routeUpdate(Update update) {
         System.out.println(update.message());
        if (isStartMessage(update.message())) {
            onStart(update.updateId(), update.message());
        } else if (update.inlineQuery() != null) {
            onInlineQuery(update.updateId(), update.inlineQuery());
        } else if (update.message() != null && update.message().replyToMessage() != null) {
            onReply(update.updateId(), update.message().chat(), update.message(), update.message().replyToMessage());
        } else if (update.message() != null && update.message().text() != null) {
            onTextMessage(update.updateId(), update.message());
        } else if (update.editedMessage() != null) {
            onEdit(update.updateId(), update.editedMessage());
        } else if (update.channelPost() != null) {
            onChannelPost(update.updateId(), update.channelPost());
        } else if (update.editedChannelPost() != null) {
            onChannelPostEdit(update.updateId(), update.editedChannelPost());
        } else if (update.callbackQuery() != null) {
            onCallbackQuery(update.updateId(), update.callbackQuery());
        } else if (update.chosenInlineResult() != null) {
            onInlineResultSelect(update.updateId(), update.inlineQuery(), update.chosenInlineResult());
        } else if (update.poll() != null) {
            onPoll(update.updateId(), update.poll());
        } else if (update.pollAnswer() != null) {
            onPollAnswer(update.updateId(), update.pollAnswer());
        } else if (update.shippingQuery() != null) {
            onShippingQuery(update.updateId(), update.shippingQuery());
        } else if (update.preCheckoutQuery() != null) {
            onPreCheckoutQuery(update.updateId(), update.preCheckoutQuery());
        } else if (update.message() != null && update.message().newChatMembers() != null) {
            for (User user : update.message().newChatMembers())
                onJoin(update.updateId(), update.message().chat(), user);
        } else if (update.message() != null && update.message().leftChatMember() != null) {
            onLeave(update.updateId(),  update.message().chat(), update.message().leftChatMember());
        } else {
            onUnknownUpdate(update);
        }

    }

    protected boolean isStartMessage(Message message) {
        return message != null && message.text() != null && message.text().startsWith("/start");
    }

}
