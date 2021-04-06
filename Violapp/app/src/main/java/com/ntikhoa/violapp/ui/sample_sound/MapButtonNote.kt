package com.ntikhoa.violapp.ui.sample_sound

import android.widget.ImageButton
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.databinding.IncludeStringBinding
import com.ntikhoa.violapp.model.Note

class MapButtonNote(
    GstrBinding: IncludeStringBinding,
    DstrBinding: IncludeStringBinding,
    AstrBinding: IncludeStringBinding,
    EstrBinding: IncludeStringBinding
) {

    val maps = hashMapOf<ImageButton, Note>(
        GstrBinding.btn0 to Note(R.drawable.str1_btn0, R.raw.str1_btn0, "G"),
        GstrBinding.btn1 to Note(R.drawable.str1_btn1, R.raw.str1_btn1, "G sharp"),
        GstrBinding.btn2 to Note(R.drawable.str1_btn2, R.raw.str1_btn2, "A"),
        GstrBinding.btn3 to Note(R.drawable.str1_btn3, R.raw.str1_btn3, "A sharp"),
        GstrBinding.btn4 to Note(R.drawable.str1_btn4, R.raw.str1_btn4, "B"),
        GstrBinding.btn5 to Note(R.drawable.str1_btn5, R.raw.str1_btn5, "C"),
        GstrBinding.btn6 to Note(R.drawable.str1_btn6, R.raw.str1_btn6, "C sharp"),
        GstrBinding.btn7 to Note(R.drawable.str2_btn0, R.raw.str2_btn0, "D"),

        DstrBinding.btn0 to Note(R.drawable.str2_btn0, R.raw.str2_btn0, "D"),
        DstrBinding.btn1 to Note(R.drawable.str2_btn1, R.raw.str2_btn1, "D sharp"),
        DstrBinding.btn2 to Note(R.drawable.str2_btn2, R.raw.str2_btn2, "E"),
        DstrBinding.btn3 to Note(R.drawable.str2_btn3, R.raw.str2_btn3, "F"),
        DstrBinding.btn4 to Note(R.drawable.str2_btn4, R.raw.str2_btn4, "F sharp"),
        DstrBinding.btn5 to Note(R.drawable.str2_btn5, R.raw.str2_btn5, "G"),
        DstrBinding.btn6 to Note(R.drawable.str2_btn6, R.raw.str2_btn6, "G sharp"),
        DstrBinding.btn7 to Note(R.drawable.str3_btn0, R.raw.str3_btn0, "A"),

        AstrBinding.btn0 to Note(R.drawable.str3_btn0, R.raw.str3_btn0, "A"),
        AstrBinding.btn1 to Note(R.drawable.str3_btn1, R.raw.str3_btn1, "A sharp"),
        AstrBinding.btn2 to Note(R.drawable.str3_btn2, R.raw.str3_btn2, "B"),
        AstrBinding.btn3 to Note(R.drawable.str3_btn3, R.raw.str3_btn3, "C"),
        AstrBinding.btn4 to Note(R.drawable.str3_btn4, R.raw.str3_btn4, "C sharp"),
        AstrBinding.btn5 to Note(R.drawable.str3_btn5, R.raw.str3_btn5, "D"),
        AstrBinding.btn6 to Note(R.drawable.str3_btn6, R.raw.str3_btn6, "D sharp"),
        AstrBinding.btn7 to Note(R.drawable.str4_btn0, R.raw.str4_btn0, "E"),

        EstrBinding.btn0 to Note(R.drawable.str4_btn0, R.raw.str4_btn0, "E"),
        EstrBinding.btn1 to Note(R.drawable.str4_btn1, R.raw.str4_btn1, "F"),
        EstrBinding.btn2 to Note(R.drawable.str4_btn2, R.raw.str4_btn2, "F sharp"),
        EstrBinding.btn3 to Note(R.drawable.str4_btn3, R.raw.str4_btn3, "G"),
        EstrBinding.btn4 to Note(R.drawable.str4_btn4, R.raw.str4_btn4, "G sharp"),
        EstrBinding.btn5 to Note(R.drawable.str4_btn5, R.raw.str4_btn5, "A"),
        EstrBinding.btn6 to Note(R.drawable.str4_btn6, R.raw.str4_btn6, "A sharp"),
        EstrBinding.btn7 to Note(R.drawable.str4_btn7, R.raw.str4_btn7, "E")
    )
}