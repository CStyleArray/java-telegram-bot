package codingwithme.java.telegram.bot;

import codingwithme.java.telegram.bot.base_handlers.BotHandler;

import java.lang.reflect.InvocationTargetException;

public class JavaTelegramBot {
    public static void registerBot(Class<? extends BotHandler> botClass, String token) {
        try {
            botClass.getConstructor(String.class).newInstance(token);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets token from environment variable. This is the recommended way to register bots
     */
    public static void registerBot(Class<? extends BotHandler> botClass) {
        try {
            botClass.getConstructor(String.class).newInstance(System.getenv("BOT_TOKEN"));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
