package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        final int LIMIT = 10; // 挑戦回数を指定
        int randomNumber = random.nextInt(1001); // ランダムな数値を指定
        int number; // プレイヤーが入力する数値
        boolean cleared = false;
        String[] foo = { "", "", "", "", "", "", "", "", "", "" };

        System.out.println("\n0〜1000の数当てゲーム（" + LIMIT + "回まで挑戦可能）");
        System.out.println("数値を入力してください\n");

        for (int i = 1; i <= LIMIT; i++) {
            System.out.print(i + "回目の挑戦 ＞");
            number = scan.nextInt(); // コマンドラインで数値を入力
            foo[i - 1] = i + "回目 " + String.valueOf(number) + "\n";

            if (number > randomNumber) {
                System.out.println("もっと小さいです\n");
            } else if (number < randomNumber) {
                System.out.println("もっと大きいです\n");
            } else if (number == randomNumber) {
                System.out.print("正解です！おめでとうございます！\n");
                cleared = true;
                break;
            }

            if (i == LIMIT) {
                System.out.print("GAME OVER… 正解は" + randomNumber + "でした\n");
            }
        }

        scan.close();

        // ファイル操作
        try {
            File file = new File("./result.txt");
            FileWriter fileWriter = new FileWriter(file);

            // ファイルに書き込み
            fileWriter.write("【数当てゲームの結果】\n\n");
            fileWriter.write("正解 " + randomNumber + "\n\n");
            for (int i = 0; i < foo.length; i++) {
                fileWriter.write(foo[i]);
            }
            if (cleared) {
                fileWriter.write("\nクリアできました");
            } else {
                fileWriter.write("\nクリアできませんでした");
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}