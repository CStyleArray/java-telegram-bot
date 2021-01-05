package codingwithme.java.telegram.bot.base_handlers;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SetWebhook;
import com.pengrad.telegrambot.response.BaseResponse;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;

import static spark.Spark.*;

public abstract class WebhookHandler extends BotHandler implements Route {
    public WebhookHandler(String token) {
        super(token);
        prepare(token);
    }


    @Override
    public Object handle(Request request, Response response) {
        try {
            Update update = BotUtils.parseUpdate(request.body());
            routeUpdate(update);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    private void prepare(String token) {
        System.out.println("Preparing webhook");
        final String portNumber = System.getenv("PORT");
        if (portNumber != null) {
            port(Integer.parseInt(portNumber));
            System.out.println("Listening on port " + portNumber);
        }

        // current app url to set webhook
        // should be set via heroku config vars
        // https://devcenter.heroku.com/articles/config-vars
        // heroku config:set APP_URL=https://app-for-my-bot.herokuapp.com
        final String appUrl = System.getenv("APP_URL");


        // set bot to listen https://my-app.heroku.com/BOTTOKEN
        // register this URL as Telegram Webhook
            post("/" + token, this);
            if (appUrl != null) {
                bot.execute(new SetWebhook().url(appUrl + "/" + token), new Callback<SetWebhook, BaseResponse>() {
                    @Override
                    public void onResponse(SetWebhook request, BaseResponse response) {
                        System.out.println(response.toString());
                    }

                    @Override
                    public void onFailure(SetWebhook request, IOException e) {
                        e.printStackTrace();
                    }
                });
                System.out.println("Setting webhook: " + appUrl + "/" + token);
            } else {
                System.err.println("Couldn't set webhook");
            }
            // can declare other routes
            get("/", (req, res) -> "index page");
            get("/hello", (req, res) -> "Hello World");
            post("/test", new Test());
            get("/test", new Test());
    }
    private static class Test implements Route {
        @Override
        public Object handle(Request request, Response response) {
            return "ok, test";
        }
    }
}
