package com.yaoshuai.tank.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class JunitTest {
    @Test
    void Test()  {

        try {
            BufferedImage bufferedImage = ImageIO.read(new File("pikaqiu.jpg"));
            assertNotNull(bufferedImage);

            BufferedImage bufferedImage1 = ImageIO.read(JunitTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));

            //断言 判断条件能否成立 条件成立能通过 条件不成立不能通过
            assertNotNull(bufferedImage1);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
