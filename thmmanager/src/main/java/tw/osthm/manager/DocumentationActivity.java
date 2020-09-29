package tw.osthm.manager;

import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.noties.markwon.Markwon;
import tw.osthm.OsThmTheme;
import tw.osthm.osthmEngine;

public class DocumentationActivity extends AppCompatActivity {

    public String documentation = " # os-thm\n" +
            " Stable version: 2.0\n" +
            " Development version: 3.0.1\n" +
            "\n" +
            " Written by members of ThatCakeID: [ryenyuku](https://github.com/ryenyuku), and [Iyxan23](https://github.com/Iyxan23).\n" +
            "\n" +
            " os-thm _(stands for: **o**pen**s**ource-**th**e**m**e)_ is a piece of software that can manage, edit, import, export or remove theme. One of the os-thm's key feature is that the theme can be applied globally, means the theme can used by apps that have implemented os-thm.\n" +
            " os-thm is intended to be open-source, rich, and easy to use.\n" +
            "\n" +
            " #### So, let's get started!\n" +
            "\n" +
            " ## For Consumers\n" +
            " os-thm is an easier way to change your theme globally for every single app that has implemented os-thm with a single click of a button.\n" +
            "\n" +
            " Make sure to have the os-thm Manager app installed, as it's supposed to be the manager for all os-thm themes. You can [download it here](https://link/to/github/releases).\n" +
            "\n" +
            " ## For Developers\n" +
            " os-thm is an easier way to make theme accessible to the user without worrying about managing themes, all of that _(adding, importing, removing, editing themes)_ are managed by os-thm, and the best part is, it's [open source](https://github.com/ThatCakeID/os-thm-android)!\n" +
            "\n" +
            " ### How to implement os-thm to your app?\n" +
            "\n" +
            " _(Note: for sketchware developers, you might want to use [os-thm-sketchware](https://github.com/ThatCakeID/os-thm-sketchware) [Currently not developed yet])_\n" +
            "\n" +
            " Add os-thm implementation to your build.gradle\n" +
            " ```gradle\n" +
            " implementation 'tw.osthm:3.0.1'\n" +
            " ```\n" +
            " That's it.\n" +
            "\n" +
            " ### How to use the library?\n" +
            " How to apply the current applied theme with os-thm\n" +
            " ```java\n" +
            " OsThmTheme currentTheme = osthmEngine.getCurrentTheme();\n" +
            " myFab.setBackgroundColor(currentTheme.colorAccent);\n" +
            " rootView.setBackgroundColor(currentTheme.colorBackground);\n" +
            " etc...\n" +
            " ```\n" +
            " We are currently finding solutions for applying theme to be less tedious, and easier.\n" +
            "\n" +
            " That's it!\n" +
            "\n" +
            " ## Colors meanings\n" +
            "\n" +
            " Here is a table showing the meanings of the colors:\n" +
            "\n" +
            " |Color Name             |Color Meaning                                                                   |\n" +
            " |-----------------------|--------------------------------------------------------------------------------|\n" +
            " |colorPrimary           |The primary color for your app (ex: actionBar)                                  |\n" +
            " |colorPrimaryDark       |The darker primary color for your app (ex: statusbar)                           |\n" +
            " |colorAccent            |The accent color for your app (ex: Button & FAB)                                |\n" +
            " |colorStatusbarTint     |The tint color of the statusbar icon (can only be black[0] and white[1])        |\n" +
            " |colorBackground        |The background color for the root container                                     |\n" +
            " |colorBackgroundText    |The text color for TextView(s) that's inside the root container                 |\n" +
            " |colorAccentText        |The text or tint color for the button/FAB's image source                        |\n" +
            " |shadow                 |The boolean value to determine if shadow is enabled or not (can only be 1 and 0)|\n" +
            " |colorControlHighlight  |The ripple color                                                                |\n" +
            " |colorHint              |The color for the EditText hint                                                 |\n" +
            " |colorPrimaryTint       |The color for the ActionBar icons or items                                      |\n" +
            " |colorBackgroundTint    |The tint color for images in a container                                        |\n" +
            " |colorBackgroundCard    |The background color for the card in a root container                           |\n" +
            " |colorBackgroundCardTint|The color tint for an ImageView in a card in a root container                   |\n" +
            " |colorBackgroundCardText|The TextView color in a card inside a container that's in the root container    |\n" +
            " |colorPrimaryCard       |The background color for a card inside an appBar                                |\n" +
            " |colorPrimaryCardTint   |The color tint for an ImageView in a card inside an appBar                      |\n" +
            " |colorPrimaryCardText   |The TextView color in a card that is inside of an appBar                        |\n" +
            "\n" +
            "\n" +
            " If you didn't understand the table above, you can use the Theme Editor on os-thm Theme Manager to determine which colors points to which.\n" +
            "\n" +
            "### Branches:\n" +
            " - master: Current latest and stable version of os-thm-android.\n" +
            " - dev: Current canary branch with newest features, bug patches, and still in development state.\n" +
            "\n" +
            " Documentation written by [Iyxan23](https://github.com/Iyxan23) and slightly edited by [ryenyuku](https://github.com/ryenyuku)\n" +
            "\n" +
            " (c) Copyright 2020 [ThatCakeID](https://github.com/ThatCakeID)\n" +
            "\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentation);

        TextView documentation_textview = findViewById(R.id.documentation_textview);
        ImageView image_back = findViewById(R.id.image_back);
        final Markwon markwon = Markwon.create(this);
        markwon.setMarkdown(documentation_textview, documentation);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        OsThmTheme theme = osthmEngine.getCurrentTheme();
        findViewById(R.id.rootView).setBackgroundColor(theme.colorBackground);
        findViewById(R.id.linear_title).setBackgroundColor(theme.colorPrimary);
        findViewById(R.id.linear_title).setElevation(theme.shadow == 1 ? ThmMgrUtils.toDip(getApplicationContext(), 5f) : 0f);

        getWindow().setStatusBarColor(theme.colorPrimaryDark);
        if (theme.colorStatusbarTint == 0
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ((TextView) findViewById(R.id.text_back)).setTextColor(theme.colorPrimaryText);
        documentation_textview.setTextColor(theme.colorBackgroundText);

        image_back.setColorFilter(theme.colorPrimaryTint);
        image_back.setBackground(new RippleDrawable(ColorStateList.valueOf(theme
                .colorControlHighlight), null, null));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            findViewById(R.id.scrollView).setVerticalScrollbarThumbDrawable(new ColorDrawable(theme.colorAccent));
        }
    }
}