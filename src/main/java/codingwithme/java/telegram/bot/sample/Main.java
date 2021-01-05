package codingwithme.java.telegram.bot.sample;

import codingwithme.java.telegram.bot.JavaTelegramBot;
import codingwithme.java.telegram.bot.sample.MyBot;

public class Main {

    public static void main(String[] args) {
        JavaTelegramBot.registerBot(MyBot.class, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    }
}
