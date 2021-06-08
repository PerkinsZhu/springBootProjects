package com.perkins

/**
 *
 * @author: perkins Zhu
 * @date: 2021/6/8 18:04
 * @description:
 **/
object BuildTest {
    @JvmStatic
    fun main(args: Array<String>) {
        (1 to 100).toList().forEach {
            println(it)
        }
    }

}