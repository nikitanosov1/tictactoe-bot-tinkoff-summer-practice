package ru.tinkoff.tictactoebottinkoffsummerpractice.bot.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.tictactoebottinkoffsummerpractice.bot.BotService;
import ru.tinkoff.tictactoebottinkoffsummerpractice.bot.Figure;
import ru.tinkoff.tictactoebottinkoffsummerpractice.config.BotConfig;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BotServiceImpl implements BotService {
    private final BotConfig botConfig;

    @Override
    public String makeTurnByGameField(String gameField) {
        int countOfFreeFields = 0;
        for (int i = 0; i < 361; i++) {
            if (gameField.equals(Figure.EMPTY)) {
                ++countOfFreeFields;
            }
        }
        if (countOfFreeFields > 100) {
            return getNewGameFieldByRecursion(gameField);
        }
        return getNewGameField(gameField);
    }

    public String getNewGameFieldByRecursion(String gameField) {
        int randomFieldIndex = (int) Math.round(Math.random() * 361);
        Figure figureInRandomFieldIndex = Figure.fromString(String.valueOf(gameField.charAt(randomFieldIndex)));
        if (figureInRandomFieldIndex.equals(Figure.EMPTY)) {
            return gameField.substring(0, randomFieldIndex) + botConfig.getFigure().getName() + gameField.substring(randomFieldIndex + 1);
        }
        return getNewGameFieldByRecursion(gameField);
    }

    public String getNewGameField(String gameField) {
        List<Integer> emptyFieldIndexes = new ArrayList<>();
        for (int i = 0; i < 361; i++) {
            Figure figure = Figure.fromString(String.valueOf(gameField.charAt(i)));
            if (figure.equals(Figure.EMPTY)) {
                emptyFieldIndexes.add(i);
            }
        }
        int randomEmptyFieldIndex = (int) Math.round(Math.random() * emptyFieldIndexes.size());
        return gameField.substring(0, emptyFieldIndexes.get(randomEmptyFieldIndex)) + botConfig.getFigure().getName() + gameField.substring(emptyFieldIndexes.get(randomEmptyFieldIndex) + 1);
    }
}
