package com.mkit.libmkit.bean;

/**
 * Created by WHF.Javas on 2017/10/13.
 */

public class HolgaItemFlag implements IHolgaItem {
    @Override
    public boolean equals(Object obj) {
        return obj==this||obj instanceof HolgaItemFlag;
    }
}
