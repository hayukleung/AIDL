// ICommonAidl.aidl
package com.hayukleung.aidl.common;

// Declare any non-default types here with import statements
import com.hayukleung.aidl.common.Common;

interface ICommonAidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void addCommon(in Common common);
    List<Common> getCommonList();
}
