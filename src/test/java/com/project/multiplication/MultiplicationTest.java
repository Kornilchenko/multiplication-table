package com.project.multiplication;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicationTest{
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
    @Ignore
    public void mainTest(String[] input, String expected) throws Exception {
        System.out.print("Result work programm " + input[0] + " = ");
        Multiplication.main(input);
        System.out.println("\ngood result = " + expected);
    }
    @Ignore
    @Test
    void mainTest1() throws Exception {
        String[] input = {"double"};
        String exp = "1.0";
        mainTest(input, exp);
    }

    @Test
    void falseInternalFileData() throws IOException {
        String [] strExternal = new String[]{"min =\n","max =5, 9\n","increment =0. 3 \n"};
        writeFileProperties(strExternal, "external.properties");
        String [] strInternalFalse = new String[]{"min =\n","max =20,8 2\n","increment =0, 8 \n"};
        writeFileProperties(strInternalFalse, "src\\main\\resources\\internal.properties");
        ReaderProperties read = new ReaderProperties("external.properties");
        assertNull(read.getMinimal());
        assertNull(read.getMaximum());
        assertNull(read.getIncrement());
        String [] strInternalTrue = new String[]{"min =1 3, 5 5\n","max =20,8 2\n","increment =0, 8 \n"};
        writeFileProperties(strInternalTrue, "src\\main\\resources\\internal.properties");
    }

    @Test
    void NoFirstArgument1() throws IOException {
        String [] str = new String[]{"min =\n","max =5, 9\n","increment =0. 3 \n"};
        writeFileProperties(str, "external.properties");
        ReaderProperties read = new ReaderProperties("external.properties");
        assertEquals(13.55, read.getMinimal());
        assertEquals(20.82, read.getMaximum());
        assertEquals(0.8, read.getIncrement());
        /*assertNull(read.getMinimal());
        assertNull(read.getMaximum());
        assertNull(read.getIncrement());*/
    }

    @Test
    void NoFirstArgument2() throws IOException {
        String [] str = new String[]{"mini =3 , 5\n","max =5, 9\n","increment =0. 3 \n"};
        writeFileProperties(str, "external.properties");
        ReaderProperties read = new ReaderProperties("external.properties");
        assertEquals(13.55, read.getMinimal());
        assertEquals(20.82, read.getMaximum());
        assertEquals(0.8, read.getIncrement());
    }

    @Test
    void NoSecondArgument1() throws IOException {
        String [] str = new String[]{"min =1.555\n","max =\n","increment =0. 3 \n"};
        writeFileProperties(str, "external.properties");
        ReaderProperties read = new ReaderProperties("external.properties");
        assertEquals(13.55, read.getMinimal());
        assertEquals(20.82, read.getMaximum());
        assertEquals(0.8, read.getIncrement());
    }

    @Test
    void NoSecondArgument2() throws IOException {
        String [] str = new String[]{"min =3 , 5\n","maximum =5, 9\n","increment =0. 3 \n"};
        writeFileProperties(str, "external.properties");
        ReaderProperties read = new ReaderProperties("external.properties");
        assertEquals(13.55, read.getMinimal());
        assertEquals(20.82, read.getMaximum());
        assertEquals(0.8, read.getIncrement());
    }

    @Test
    void NoThirArgument1() throws IOException {
        String [] str = new String[]{"min =1.555\n","max =55  ,9 8\n","increment = \n"};
        writeFileProperties(str, "external.properties");
        ReaderProperties read = new ReaderProperties("external.properties");
        assertEquals(13.55, read.getMinimal());
        assertEquals(20.82, read.getMaximum());
        assertEquals(0.8, read.getIncrement());

    }

    @Test
    void NoThirdArgument2() throws IOException {
        String [] str = new String[]{"min =3 , 5\n","max =5, 9\n","incrementik =0. 3 \n"};
        writeFileProperties(str, "external.properties");
        ReaderProperties read = new ReaderProperties("external.properties");
        assertEquals(13.55, read.getMinimal());
        assertEquals(20.82, read.getMaximum());
        assertEquals(0.8, read.getIncrement());
    }

    @Test
    void testTrue() throws IOException {
        String [] str = new String[]{"min =3 , 5\n","max =5, 9\n","increment =0. 3 \n"};
        writeFileProperties(str, "external.properties");
        ReaderProperties read = new ReaderProperties("external.properties");
        assertEquals(3.5, read.getMinimal());
        assertEquals(5.9, read.getMaximum());
        assertEquals(0.3, read.getIncrement());
    }
}