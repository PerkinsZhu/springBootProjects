package com.perkins.utils.excel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: perkins Zhu
 * @date: 2021/5/31 12:58
 * @description:
 **/
public class ExcelApp {

    @Test
    public void test() {
        List<DictData> list = new ArrayList<>(0);
        list.add(new DictData());
        ExcelUtil<DictData> util = new ExcelUtil<DictData>(DictData.class);
        util.exportExcel(list, "dictData");

    }
}
