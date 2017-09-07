package com.blogspot.illyakeeplearning.suffixtree;

public class MemoryHelper {

    public interface ObjectInitializer {
        public Object init();
    }

    public static void runGC() throws Exception {
        // It helps to call Runtime.gc()
        // using several method calls:
        for (int r = 0; r < 4; ++r)
            _runGC();
    }

    private static void _runGC() throws Exception {
        long usedMem1 = usedMemory(), usedMem2 = Long.MAX_VALUE;
        for (int i = 0; (usedMem1 < usedMem2) && (i < 500); ++i) {
            s_runtime.runFinalization();
            s_runtime.gc();
            Thread.currentThread().yield();

            usedMem2 = usedMem1;
            usedMem1 = usedMemory();
        }
    }

    public static long usedMemory() {
        return s_runtime.totalMemory() - s_runtime.freeMemory();
    }

    public static long sizeOf(int count, ObjectInitializer initializer) throws Exception {
        // Warm up all classes/methods we will use
        runGC();
        usedMemory();

        // Array to keep strong references to allocated objects
        Object[] objects = new Object[count];

        long heap1 = 0;
        // Allocate count+1 objects, discard the first one
        for (int i = -1; i < count; ++i) {
            Object object = null;

            // Instantiate your data here and assign it to object
            object = initializer.init();
            if (i >= 0) {
                objects[i] = object;
            } else {
                // Discard the warm up object
                object = null;

                runGC();
                // Take a before heap snapshot
                heap1 = usedMemory();
            }
        }
        runGC();

        // Take an after heap snapshot
        long heap2 = usedMemory();

        final int size = Math.round(((float) (heap2 - heap1)) / count);

        for (int i = 0; i < count; ++i)
            objects[i] = null;
        objects = null;

        return size;
    }

    private static final Runtime s_runtime = Runtime.getRuntime();

}
