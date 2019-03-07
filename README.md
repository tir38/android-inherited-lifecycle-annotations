# @OnLifecycleEvent annotations and inheritance

Using [@OnLifecycleEvent](https://developer.android.com/reference/android/arch/lifecycle/OnLifecycleEvent) annotations we can listen for lifecycle events from a LifecycleOwner:


```
class WithAnnotations implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void didCreate() {
        Log.d("WithAnnotations", "didCreate");
    }
}
```

```
public class MainFragment extends Fragment {
    @Override
    public View onCreateView(...) {
        WithAnnotations testClass = new WithAnnotations();
        getLifecycle().addObserver(testClass);
        ...
    }
}
```

But can these same annotations be inherited from another class?


```
class WithInheritedAnnotations extends MyAbstractClass {
    @Override
    public void didCreate() {
        super.didCreate();
        Log.d("WithInheritedAnnotations", "didCreate");
    }
}
```

```
abstract class MyAbstractClass implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void didCreate() {
        Log.d("MyAbstractClass", "didCreate");
    }
}
```

What if they are inherited *from an interface*?

```
class WithInheritedAnnotations implements MyInterface {
    @Override
    public void didCreate() {
        Log.d("WithInheritedAnnotations", "didCreate");
    }
}
```

```
interface MyInterface extends LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void didCreate();
}
```

For good measure, what if we have an abstract class *which in-turn inherits them from an interface*?

```
class WithInheritedAnnotations extends MyAbstractClass{
    @Override
    public void didCreate() {
        super.didCreate();
        Log.d("WithInheritedAnnotations", "didCreate");
    }
}
```

```
abstract class MyAbstractClass implements MyInterface {
    @Override
    public void didCreate() {
        Log.d("MyAbstractClass", "didCreate");
    }
}
```

```
interface MyInterface extends LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void didCreate();
}
```

In all of these cases the answer is "Yes". These annotations still work.

Why?

1. `OnLifecycleEvent` is not marked [`@Inherited`](https://docs.oracle.com/javase/8/docs/api/index.html) Why does it work for the abstract class?
2. If I understand correctly annotations can't be inherited from an interface:

	>Note that this meta-annotation type has no effect if the annotated type is used to annotate anything other than a class. Note also that this meta-annotation only causes annotations to be inherited from superclasses; annotations on implemented interfaces have no effect.

	So why does the interface inheritance work too?
	
	
***All of this was tested with against `android.arch.lifecycle:common:1.1.1`***