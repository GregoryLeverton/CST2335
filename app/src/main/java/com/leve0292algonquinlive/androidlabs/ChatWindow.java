package com.leve0292algonquinlive.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends Activity {
    ArrayList<String> messages = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        ListView chat = findViewById(R.id.chat);
        EditText textInput = findViewById(R.id.chatText);
        Button send = findViewById(R.id.send_button);
        ChatAdapter messageAdapter =new ChatAdapter( this );
        chat.setAdapter (messageAdapter);

        send.setOnClickListener(e ->{
                messages.add(textInput.getText().toString());
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount() & getView()
                textInput.setText("");
            }
        );



    }

    private class ChatAdapter extends ArrayAdapter<String>{
         ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount(){
            return messages.size();
        }

        public String getItem(int position){
            return messages.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null ;

            if(position%2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);

            TextView message = result.findViewById(R.id.message_text);
            message.setText(   getItem(position)  ); // get the string at position
            return result;

        }

        public long getId(int position){
            return position;
        }

    }
}
