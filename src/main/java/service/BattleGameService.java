package service;

import dto.SizeDto;

public class BattleGameService {

    public SizeDto setInitGame() {
        return SizeDto.newInstance(5);
    }
}
