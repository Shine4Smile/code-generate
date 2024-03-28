package com.simple.maker.generate;

import com.simple.maker.meta.Meta;
import com.simple.maker.meta.MetaManager;

public class MainGenerator {
    public static void main(String[] args) {
        Meta meta = MetaManager.getMetaObject();
        System.out.println(meta);
    }
}
