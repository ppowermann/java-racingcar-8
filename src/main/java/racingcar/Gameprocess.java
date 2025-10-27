package racingcar;

import camp.nextstep.edu.missionutils.Console;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

public class Gameprocess {
    int gameCount; //게임횟수
    String[] playerNames;  //플레이어들을 모아놓은 배열
    String[] carResults; //자동차전진 '---'
    String[] winners; //승자 2명이상일수도 잇으니 배열로

    Gameprocess() {
        carNameSet();
        gameCountSetting();
        gamePlaying();
        resultWinners();
    }

    //자동차이름셋팅메서드
    void carNameSet() {
        int commaCount = 0;
        System.out.print("경주할 자동차 이름(이름은 쉼표(,) 기준으로 구분)    :");
        String inputStringValue = readLine();
        StringBuilder stringBuilderb = new StringBuilder();

        //ex) inputStringValue =
        // a,b,c로 입력시 콤마는 두개 이지만 출전자는 3명이으로
        // 반복문으로 , 2개 감지하고 끝나고 의도적으로 1추가해줘서
        //    출전자 숫자로 만든다음 그 만큼의 크기의 배열을 생성함
        for (int i = 0; i < inputStringValue.length(); i++) {
            if (inputStringValue.charAt(i) == ',') {
                commaCount++;//반복문으로 2까지 올리고
            }
        }
        commaCount++; //의도적으로 한개더 추가해서 출전자 숫자와 같은 3을 만듬
        playerNames = new String[commaCount];
        carResults = new String[commaCount];//전진값'-'을 담을 배열도 하나 추가함
        //===============여기까지 해당크기만큼의 배열생성로직===========

        //입력값 inputStringValue 을 char로 쪼개서 ,를 만나면 스트링빌더로 배열0번째에 저장함
        int carName = 0;
        for (int i = 0; i < inputStringValue.length(); i++) {
            char ch = inputStringValue.charAt(i);
            if (ch == ',') {
                playerNames[carName] = stringBuilderb.toString();
                carName++;
                stringBuilderb.setLength(0);
            } else {
                stringBuilderb.append(ch);
            }
        }
        //마지막은 ,이 없으니 바로 ,없이 저장
        playerNames[carName] = stringBuilderb.toString();

    }


    //게임횟수셋팅 메서드
    void gameCountSetting() {
        System.out.print("시도할 횟수는 몇 회인가요?  :");
        gameCount = Integer.parseInt(Console.readLine());
        ///두번째 게임로직은 특별히 할것 없는 것 같아 커밋함
    }

    void gamePlaying() {

        //Null 안나오게 초기화 한다
        for (int i = 0; i < playerNames.length; i++) {
            carResults[i] = "";
        }

        //이중포문 바깥for는 게임횟수 안for는 주사위결과 승리시 '-' 추가 하는 로직
        for (int j = 0; j < gameCount; j++) {
            for (int k = 0; k < playerNames.length; k++) {
                if (pickNumberInRange(0, 9) >= 4) {
                    carResults[k] += '-';
                }
                System.out.println(playerNames[k] + ":" + carResults[k]);
            }
            System.out.println();
        }

    }

    void resultWinners() {
        int maxLength = 0;
        int winnerCount = 0;

        //가장높은 전진값 추출
        for (String result : carResults) {
            if (result.length() > maxLength) {
                maxLength = result.length();
            }
        }

        //가장 높은값 추출한걸로 동점자 수 크기만큼 배열생성
        for (int i = 0; i < playerNames.length; i++) {
            if (maxLength == carResults[i].length()) {
                winnerCount++;
            }
        }
        winners = new String[winnerCount];


        int index = 0;
        for (int i = 0; i < playerNames.length; i++) {
            if (maxLength == carResults[i].length()) {
                winners[index] = playerNames[i];
                index++;
            }

        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < winners.length; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(winners[i]);
        }
        System.out.println("최종 우승자 : " + result);
    }
}

