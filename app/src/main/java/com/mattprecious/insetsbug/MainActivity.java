package com.mattprecious.insetsbug;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;

public class MainActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    View view = new View(this);
    view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
      @Override public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
        insets = insets.consumeSystemWindowInsets();
        insets = insets.consumeStableInsets();

        if (Build.VERSION.SDK_INT >= 28) {
          insets = insets.consumeDisplayCutout();
        }

        WindowInsets copiedInsets = new WindowInsets(insets);

        if (insets.isConsumed() != copiedInsets.isConsumed()) {
          throw new AssertionError();
        }

        return insets;
      }
    });

    setContentView(view);
  }
}
