package com.simple.maker;

import com.simple.maker.generate.main.MainGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author Simple
 */
public class Main {

    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.doGenerate();
    }
}
