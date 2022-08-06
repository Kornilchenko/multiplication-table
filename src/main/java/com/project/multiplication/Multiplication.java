package com.project.multiplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class Multiplication {
    private static final Logger log = LoggerFactory.getLogger(Multiplication.class);

    public static void main(String[] args) throws Exception {
        log.info("start Programm");
        log.info("read properties");
        ReaderProperties property = new ReaderProperties("external.properties");
        if (property.getMinimal() == null)
            log.error("no values in properties, program exit");
        else if(property.getMinimal()> property.getMaximum())
            log.error("variable error minimum greater than maximum");
        else if(property.getMaximum()- property.getMinimal()<= property.getIncrement())
            log.error("the increment is greater than or equal to the difference between the maximum and minimum values");
        else
            multiplication(property, readingArguments(args));
    }

    private static char readingArguments(String[] argumens) {
        if (argumens.length == 0)
            return 'i';
        for (String arg : argumens) {
            if (arg.equalsIgnoreCase("int") || arg.equalsIgnoreCase("integer")) {
                log.info("type variable int");
                return 'i';
            }
            if (arg.equalsIgnoreCase("double")) {
                log.info("type variable double");
                return 'd';
            }
            if (arg.equalsIgnoreCase("float")) {
                log.info("type variable float");
                return 'f';
            }
        }
        log.info("default type variable int");
        return 'i';
    }

    private static void multiplication(ReaderProperties property, char type) {
        double minimal = property.getMinimal();
        double maximal = property.getMaximum();
        double increment = property.getIncrement();

        if (type == 'i') {
            int min = (int) minimal;
            int max = (int) maximal;
            int inc = (int) increment;
            for (int n = min; n <= max; n += inc) {
                for (int m = min; m <= max; m += inc) {
                    System.out.print(m * n + "\t");
                }
                System.out.println();
            }
        } else if (type == 'f' || type == 'd') {
            for (double n = minimal; n <= maximal; n += increment) {
                for (double m = minimal; m <= maximal; m += increment) {
                    System.out.printf("%.3f", m * n);
                    System.out.print("\t");
                }
                System.out.println();
            }
        }
    }
}
