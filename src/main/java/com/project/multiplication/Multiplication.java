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
        log.debug("start Programm");
        log.debug("read properties");
        ReaderProperties property = new ReaderProperties("external.properties");

        MultiplicationTable multable = new MultiplicationTable(property.getMinimal(),
                property.getMaximum(), property.getIncrement(), args);
        multable.showMultiplicationTable();
    }
}
