package expert.android.quoccuong.memoryleak;

import android.content.Context;

/**
 * Created by QUOC CUONG on 08/03/2018.
 */

public class SomeRandomSampleClass {

    private static SomeRandomSampleClass someRandomSampleClass;
    private Context context;

    // private: No outside class can create new instance
    private SomeRandomSampleClass(Context context) {
        this.context = context;
    }

    public static SomeRandomSampleClass getSomeRandomSampleClass(Context context) {
        if (someRandomSampleClass == null) {
            someRandomSampleClass = new SomeRandomSampleClass(context);
        }
        return someRandomSampleClass;
    }
}
