package codingwithme.java.telegram.bot.base_handlers;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.DeleteWebhook;
import com.pengrad.telegrambot.response.BaseResponse;

import java.io.IOException;

public abstract class LongPollingHandler extends BotHandler {

    public LongPollingHandler(String token) {
        super(token);
        prepare(token);
    }

    private void prepare(String token) {
        TelegramBot bot = new TelegramBot(token);
        //deleting existing webhook
        bot.execute(new DeleteWebhook(), new Callback<DeleteWebhook, BaseResponse>() {
            @Override
            public void onResponse(DeleteWebhook request, BaseResponse response) {
                //Setting long polling enabled
                System.out.println("Successfully started long polling");
                bot.setUpdatesListener(updates -> {
                    for (Update update : updates) {
                        routeUpdate(update);
                    }
                    return UpdatesListener.CONFIRMED_UPDATES_ALL;
                });
            }

            @Override
            public void onFailure(DeleteWebhook request, IOException e) {
                System.err.println("Couldn't delete existing webhook: " + e.getMessage());
                System.exit(0);
            }
        });

    }


}
