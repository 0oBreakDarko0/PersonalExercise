package org.myself.huawei.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。
 * 小张手上有n个空汽水瓶，她想知道自己最多可以喝到多少瓶汽水。
 */
public class ExchangeBottle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        List<Integer> bottles = new ArrayList<>();
        while (in.hasNext()) {
            int emptyBottle = in.nextInt();

            if (emptyBottle == 0) {
                break;
            }

            bottles.add(emptyBottle);
        }

        bottles.forEach(empty -> {
            System.out.println(solution(empty));
        });
    }

    private static int solution(int emptyBottle) {
        //3个空瓶可以换1瓶汽水，可以向老板借空汽水瓶，但是要还
        if (emptyBottle <= 1) {
            return 0;
        }

        int result = 0;

        while (emptyBottle >= 2) {
            //可以换多少瓶汽水
            int canExchangeBottle = emptyBottle / 3;
            result += canExchangeBottle;
            //喝完之后还剩下多少空瓶
            emptyBottle = emptyBottle % 3 + canExchangeBottle;

            if (emptyBottle == 2) {
                result ++;
                emptyBottle = 0;
            }
        }

        return result;

    }
}
