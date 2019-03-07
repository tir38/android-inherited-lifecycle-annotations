package com.example;

import android.util.Log;

class WithInheritedAnnotations extends MyAbstractClass {
    @Override
    public void didCreate() {
        super.didCreate();
        Log.d("WithInheritedAnnotations", "didCreate");
    }
}
