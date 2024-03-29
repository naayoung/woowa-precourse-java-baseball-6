package baseball;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;


public class Application {
    static RandomNum rn = new RandomNum();
    static List<Integer> nums = rn.randomNum();
    public static void main(String[] args) {
        InputNum in = new InputNum();

        System.out.println("숫자 야구 게임을 시작합니다.");

        while (true) {
            nums = rn.randomNum();
            while (true) {
                System.out.print("숫자를 입력해주세요 : ");
                String inputNum = in.getInputNum();
                int count = 0;

                //숫자에 0이 포함된 경우 재입력 요청
                for(int i=0; i<3; i++){
                    if (inputNum.charAt(i) - '0' == 0){
                        count += 1;
                    }
                }
                if (count >= 1){
                    System.out.println("재입력해주세요.");
                } else if (ballCount(inputNum)) {
                    System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                    break;
                }
            }
            if (!restart()) {
                return;
            }
        }
    }

    //플레이
    static boolean ballCount(String inputNum) {
        int strike = 0;
        int ball = 0;

        for (int i = 0; i < 3; i++) {
            if (inputNum.charAt(i) - '0' == nums.get(i)) {
                strike += 1;
            } else if (nums.contains(inputNum.charAt(i) - '0')) {
                ball += 1;
            }
        }
        if (ball != 0) {
            System.out.print(ball + "볼 ");
        }
        if (strike != 0) {
            System.out.print(strike + "스트라이크");
        }
        if (ball == 0 && strike == 0) {
            System.out.print("낫싱");
        }
        System.out.println();

        if (strike == 3) {
            return true;
        }
        return false;
    }

    //재시작
    static boolean restart() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String selectNum;
        try {
            selectNum = Console.readLine();
        } catch (IllegalArgumentException e) {
            return false;
        }
        if ("1".equals(selectNum)) {
            return true;
        } else if ("2".equals(selectNum)) {
            return false;
        }
        return false;
    }

}
