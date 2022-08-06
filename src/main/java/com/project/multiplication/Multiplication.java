package com.project.multiplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Locale;


/*
Реалізувати програму, яка видасть в консоль табличку множення для чисел,
тип яких задається системною змінною (по дефолту int),
а мінімум, максимум та інкремент задаються у файлі властивостей.
Створити git-репо на вибраному ресурсі Github  Gitlab  GitBacket
Налаштувати будь-який CI і зібрати проєкт із гілки master
Покрити тестами код
Створити гілку dev і внести в ній зміни згідно рекомендацій Sonar
використовуючи можливості рефакторингу IDE
Створити мерж-реквест із dev до master
Запустіть, що змержили на іншому комп’ютері автоматично, using Jenkins.
 */
public class Multiplication
{
    private static final Logger log = LoggerFactory.getLogger(Multiplication.class);
    public static void main( String[] args ) throws IOException {
        log.info("start Programm");
        ReaderProperties  property = new ReaderProperties();

        char type = readingArguments(args);
        if(type == 'i'){

        }


        int min = property.getMinimal();
        int max  = property.getMaximum();
        int inc = property.getIncrement();
        log.info("get all properties");
        for(int n=min;n<=max; n+=inc){
            for(int m =min; m<=max; m+=inc){
                System.out.print(m*n + "\t");
            }
            System.out.println();
        }
        var tryu=5;
    }

    private static char readingArguments(String[] argumens){
        if(argumens.length == 0)
            return 'i';
        for(String arg: argumens) {
            if (arg.toLowerCase().equals("int") || arg.toLowerCase().equals("integer"))
                return 'i';
            if (arg.toLowerCase().equals("double"))
                return 'd';
            if (arg.toLowerCase().equals("float"))
                return 'f';
        }
        return 'i';
    }
}