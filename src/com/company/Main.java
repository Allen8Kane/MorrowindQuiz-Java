package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DbConnect dbConnect = new DbConnect();
        dbConnect.open("questions.db");
        dbConnect.selectAllQuestions();
        ArrayList<Question> questions = new ArrayList<Question>();
        questions = dbConnect.selectAllQuestions();
        ArrayList<PlayerClass> playerClasses = new ArrayList<PlayerClass>();
        playerClasses = dbConnect.selectAllClasses();
        int i = 1;
        int tmpAnswerCombat = 0;
        int tmpAnswerMagic = 0;
        int tmpAnswerStealth = 0;
        PlayerAnswer playerAnswer = new PlayerAnswer();
        Scanner in = new Scanner(System.in);
        for (var item : questions) {
            System.out.println(i + ". " + item.Question + "\n" + item.Combat + "\n" + item.Magic + "\n" + item.Stealth + "\n");
            while (true){
                int scannerAnswer = in.nextInt();
                if(scannerAnswer > 0 && scannerAnswer < 4){
                    if (scannerAnswer == 1 && tmpAnswerCombat < 7)
                    {
                        tmpAnswerCombat++;

                    }else if(scannerAnswer == 2 && tmpAnswerMagic < 7)
                    {
                        tmpAnswerMagic++;
                    }else if(scannerAnswer == 3 && tmpAnswerStealth < 7)
                    {
                        tmpAnswerStealth++;
                    }
                    break;
                }
                System.out.println("Некорректное число");
            }
            i++;
        }
        i = 1;
        playerAnswer.Combat = Integer.toString(tmpAnswerCombat);
        playerAnswer.Magic = Integer.toString(tmpAnswerMagic);
        playerAnswer.Stealth = Integer.toString(tmpAnswerStealth);
        if (playerAnswer.Combat.equals("7"))
        {
            System.out.println("Ваш класс войн");
        }
        if (playerAnswer.Magic.equals("7"))
        {
            System.out.println("Ваш класс Маг");
        }
        if (playerAnswer.Stealth.equals("7"))
        {
            System.out.println("Ваш класс Вор");
        }
        if(tmpAnswerCombat == 4 && tmpAnswerMagic < 7 && tmpAnswerStealth < 7)
        {
            System.out.println("Ваш класс жулик");
        }
        for (var item : playerClasses)
        {
            if(item.Combat.equals(playerAnswer.Combat) && item.Magic.equals(playerAnswer.Magic) && item.Stealth.equals(playerAnswer.Stealth)){
                System.out.println("Ваш класс: " + item.Name);
            }
        }
        dbConnect.close();
        in.close();
    }
}
