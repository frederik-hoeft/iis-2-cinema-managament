package IIS.Server.utils;

class DeepCopyMapping<T, U> {
    private final T sourceGetter;
    private final U targetSetter;

    public DeepCopyMapping(T source, U target) {
        this.sourceGetter = source;
        this.targetSetter = target;
    }

    public T getSourceGetter() {
        return sourceGetter;
    }

    public U getTargetSetter() {
        return targetSetter;
    }
}