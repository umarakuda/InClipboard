package com.ra9dam.inclipboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText textBox;
    //private ClipboardManager clipboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textBox = findViewById(R.id.editTextTextMultiLine);
        textBox.requestFocus();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus){
            //code to be executed when window has gained focus
            PastePlainText();
        }
    }
    private void PastePlainText(){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
        String pasteData = (String) item.getText();

        if (pasteData != null){
            textBox.setText(pasteData);
        }
        textBox.addTextChangedListener( new TextWatcher() {

            // 文字列sのなかで startの位置から開始されている字数countの文字が、
            // 字数lengthの古いテキストを置換するときに呼び出される。
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }

            // 文字列sのなかで startの位置から開始されている字数countの文字が、
            // 字数lengthの新しいテキストで置換されようとしているときに呼び出される。
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            // 文字列sのどこかで、テキストが変更されたときに呼び出される。
            @Override
            public void afterTextChanged(Editable s) {
                // テキスト変更後に変更されたテキストを取り出す
                String inputStr= s.toString();
                // Creates a new text clip to put on the clipboard
                clipboard.setPrimaryClip(ClipData.newPlainText("simple text", inputStr));
            }
        } );
    }
}