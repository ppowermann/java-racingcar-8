package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

public class Gameprocess {
    int gameCount;


    Gameprocess() {
        carNameSet();
        gameCountSetting();
        gamePlaying();
        gameResult();
    }


    //자동차이름셋팅메서드
    void carNameSet() {
        System.out.print("경주할 자동차 이름(이름은 쉼표(,) 기준으로 구분)    :");
        readLine();
    }

    //게임횟수셋팅 메서드
    void gameCountSetting() {
        System.out.print("시도할 횟수는 몇 회인가요?  :");
        gameCount = Integer.parseInt(Console.readLine());
    }

    void gamePlaying() {
        pickNumberInRange(0, 9);
    }

    void gameResult() {
        System.out.println("최종 우승자 : ");
    }

}
