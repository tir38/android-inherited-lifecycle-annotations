package com.example;

import android.util.Log;

abstract class MyAbstractClass implements MyInterface {
    @Override
    public void didCreate() {
        Log.d("MyAbstractClass", "didCreate");
    }
}
