package com.project.multiplication;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestMultiplication {
    public void writeFileProperties(String[] dataFile, String path){
        try(FileWriter writer = new FileWriter(path)){
            writer.write(dataFile[0]);
            writer.write(dataFile[1]);
            writer.write(dataFile[2]);
            writer.flush();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void noArgumentMinInExternalFile() throws IOException {
        String [] str = new String[]{"min =\n","max =8, 9\n","increment =1. 3 \n"};
        writeFileProperties(str, "test_external.properties");
        ReaderProperties read = new ReaderProperties("test_external.properties",
                "test.properties");
        assertEquals(13.55, read.getMinimal());
        assertEquals(20.82, read.getMaximum());
        assertEquals(1.8, read.getIncrement());
    }

    @Test
    void bedArgumentMinInExternalFile() throws IOException {
        String [] str = new String[]{"argumentMini =3 , 5\n","max =8, 9\n","increment =1. 3 \n"};
        writeFileProperties(str, "test_external.properties");
        ReaderProperties read = new ReaderProperties("test_external.properties",
                "test.properties");
        assertEquals(13.55, read.getMinimal());
        assertEquals(20.82, read.getMaximum());
        assertEquals(1.8, read.getIncrement());
    }

    @Test
    void noArgumentMaxInExternalFile() throws IOException {
        String [] str = new String[]{"min =1.555\n","max =\n","increment =1. 3 \n"};
        writeFileProperties(str, "test_external.properties");
        ReaderProperties read = new ReaderProperties("test_external.properties",
                "test.properties");
        assertEquals(13.55, read.getMinimal());
        assertEquals(20.82, read.getMaximum());
        assertEquals(1.8, read.getIncrement());
    }

    @Test
    void bedArgumentMaxInExternalFile() throws IOException {
        String [] str = new String[]{"min =3 , 5\n","maximum =8, 9\n","increment =1. 3 \n"};
        writeFileProperties(str, "test_external.properties");
        ReaderProperties read = new ReaderProperties("test_external.properties",
                "test.properties");
        assertEquals(13.55, read.getMinimal());
        assertEquals(20.82, read.getMaximum());
        assertEquals(1.8, read.getIncrement());
    }

    @Test
    void noArgumentIncrementInExternalFile() throws IOException {
        String [] str = new String[]{"min =1.555\n","max =55  ,9 8\n","increment = \n"};
        writeFileProperties(str, "test_external.properties");
        ReaderProperties read = new ReaderProperties("test_external.properties",
                "test.properties");
        assertEquals(13.55, read.getMinimal());
        assertEquals(20.82, read.getMaximum());
        assertEquals(1.8, read.getIncrement());

    }

    @Test
    void bedArgumentIncrementInExternalFile() throws IOException {
        String [] str = new String[]{"min =3 , 5\n","max =8, 9\n","incrementik =1. 3 \n"};
        writeFileProperties(str, "test_external.properties");
        ReaderProperties read = new ReaderProperties("test_external.properties",
                "test.properties");
        assertEquals(13.55, read.getMinimal());
        assertEquals(20.82, read.getMaximum());
        assertEquals(1.8, read.getIncrement());
    }

    @Test
    void allArgumentsGood() throws IOException {
        String [] str = new String[]{"min =3 , 5\n","max =8, 9\n","increment =1. 3 \n"};
        writeFileProperties(str, "test_external.properties");
        ReaderProperties read = new ReaderProperties("test_external.properties",
                "test.properties");
        assertEquals(3.5, read.getMinimal());
        assertEquals(8.9, read.getMaximum());
        assertEquals(1.3, read.getIncrement());
    }
    /*@Test
    void falseInternalFileData() throws IOException {
        String [] strExternal = new String[]{"min =\n","max =5, 9\n","increment =0. 3 \n"};
        writeFileProperties(strExternal, "test_external.properties");
        String [] strInternalFalse = new String[]{"min =\n","max =20,8 2\n","increment =0, 8 \n"};
        writeFileProperties(strInternalFalse, "src\\main\\resources\\test.properties");
        ReaderProperties read = new ReaderProperties("test_external.properties",
                "test.properties");
        Assertions.assertNull(read.getMinimal());
        Assertions.assertNull(read.getMaximum());
        Assertions.assertNull(read.getIncrement());
        String [] strInternalTrue = new String[]{"min =13,55\n","max =20,82\n","increment =1,8 \n"};
        writeFileProperties(strInternalTrue, "src\\main\\resources\\test.properties");
    }*/

    @Test
    void bigIncrement() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        String [] arr = new String[]{"abracadabra"};
        MultiplicationTable mt = new MultiplicationTable(3.3, 4.6,5.0, arr);
        mt.showMultiplicationTable();

        System.out.flush();
        System.setOut(old);
        String str = baos.toString().replace("\n", "").replace("\r", "");
        assertEquals("the increment is greater than or equal to the difference between the maximum and minimum values",
                str);
    }
    @Test
    void minimumIsGreaterThanMaximum() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        String [] arr = new String[]{"abracadabra"};
        MultiplicationTable mt = new MultiplicationTable(126.6, 20.567,10.56, arr);
        mt.showMultiplicationTable();

        System.out.flush();
        System.setOut(old);
        String str = baos.toString().replace("\n", "").replace("\r", "");
        assertEquals("variable error minimum greater than maximum",str);
    }

    @Test
    void argumentNull() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        String [] arr = new String[]{"abracadabra"};
        MultiplicationTable mt = new MultiplicationTable(null, null,null, arr);
        mt.showMultiplicationTable();

        System.out.flush();
        System.setOut(old);
        String str = baos.toString().replace("\n", "").replace("\r", "");
        assertEquals("no values in properties, program exit",str);
    }

    @Test
    void incrementLessThanOrEqualToZero() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        String [] arr = new String[]{"abracadabra"};
        MultiplicationTable mt = new MultiplicationTable(45.5, 100.3,0.0, arr);
        mt.showMultiplicationTable();

        System.out.flush();
        System.setOut(old);
        String str = baos.toString().replace("\n", "").replace("\r", "");
        assertEquals("increment is less than or equal to zero", str);

        MultiplicationTable mt2 = new MultiplicationTable(45.5, 100.3,-12.5, arr);
        mt.showMultiplicationTable();

        System.out.flush();
        System.setOut(old);
        String str2 = baos.toString().replace("\n", "").replace("\r", "");
        assertEquals("increment is less than or equal to zero", str2);
    }

    @Test
    void typeInteger() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        String [] arr = new String[]{"int"};
        MultiplicationTable mt = new MultiplicationTable(2.0, 8.0,2.0, arr);
        mt.showMultiplicationTable();

        System.out.flush();
        System.setOut(old);
        String str = baos.toString().replace("\r", "");
        assertEquals("4\t8\t12\t16\t\n" +
                "8\t16\t24\t32\t\n" +
                "12\t24\t36\t48\t\n" +
                "16\t32\t48\t64\t\n", str);
    }

    @Test
    void typeDouble() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        String [] arr = new String[]{"double"};
        MultiplicationTable mt = new MultiplicationTable(2.25, 8.789,2.0, arr);
        mt.showMultiplicationTable();

        System.out.flush();
        System.setOut(old);
        String str = baos.toString().replace("\r", "").replace(",", ".");
        assertEquals("5.0625\t9.5625\t14.0625\t18.5625\t\n" +
                "9.5625\t18.0625\t26.5625\t35.0625\t\n" +
                "14.0625\t26.5625\t39.0625\t51.5625\t\n" +
                "18.5625\t35.0625\t51.5625\t68.0625\t\n", str);
    }

    @Test
    void typeFloat() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        String [] arr = new String[]{"float"};
        MultiplicationTable mt = new MultiplicationTable(2.0, 8.0,2.0, arr);
        mt.showMultiplicationTable();

        System.out.flush();
        System.setOut(old);
        String str = baos.toString().replace("\r", "").replace(",", ".");
        assertEquals("4.0000\t8.0000\t12.0000\t16.0000\t\n" +
                "8.0000\t16.0000\t24.0000\t32.0000\t\n" +
                "12.0000\t24.0000\t36.0000\t48.0000\t\n" +
                "16.0000\t32.0000\t48.0000\t64.0000\t\n", str);
    }

    @Test
    void defoultType() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        String [] arr = new String[]{"abrakadabra"};
        MultiplicationTable mt = new MultiplicationTable(2.0, 8.0,2.0, arr);
        mt.showMultiplicationTable();

        System.out.flush();
        System.setOut(old);
        String str = baos.toString().replace("\r", "");
        assertEquals("4\t8\t12\t16\t\n" +
                "8\t16\t24\t32\t\n" +
                "12\t24\t36\t48\t\n" +
                "16\t32\t48\t64\t\n", str);
    }
}

