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

        if (inputStringValue == null || inputStringValue.trim().isEmpty()) {
            throw new IllegalArgumentException("입력값을 넣으세요");
        }


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
            char char1 = inputStringValue.charAt(i);
            if (char1 == ',') {
                String name = stringBuilderb.toString().trim();
                validateCarName(name);
                playerNames[carName] = name;
                carName++;
                stringBuilderb.setLength(0);
            } else {
                stringBuilderb.append(char1);
            }
        }
        //마지막은 ,이 없으니 바로 ,없이 저장
        String lastName = stringBuilderb.toString().trim();
        playerNames[carName] = lastName;

        //마지막 경우도 예외처리

        if (lastName.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 비어있을 수 없습니다.");
        }
        if (lastName.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5자 이하만 가능합니다.");
        }

    }

    void validateCarName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 비어있을 수 없습니다.");
        }
        if (name.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5자 이하만 가능합니다.");
        }
    }


    //게임횟수셋팅 메서드
    void gameCountSetting() {

        //숫자가 아닌값을 입력 오류발생
        try {
            System.out.print("시도할 횟수는 몇 회인가요?  :");
            gameCount = Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }

        //영과 음수 입력시 오류
        if (gameCount <= 0) {
            throw new IllegalArgumentException("게임 횟수는 1 이상이어야 합니다.");
        }
    }

    void moveCar(int carIndex) {
        if (pickNumberInRange(0, 9) >= 4) {
            carResults[carIndex] += '-';
        }
    }

    void gamePlaying() {
        System.out.println("\n실행 결과");

        //Null 안나오게 초기화 한다
        for (int i = 0; i < playerNames.length; i++) {
            carResults[i] = "";
        }

        //이중포문 바깥for는 게임횟수 안for는 주사위결과 승리시 '-' 추가 하는 로직
        for (int j = 0; j < gameCount; j++) {
            for (int k = 0; k < playerNames.length; k++) {
                moveCar(k);
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

