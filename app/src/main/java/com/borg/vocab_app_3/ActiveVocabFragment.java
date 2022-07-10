package com.borg.vocab_app_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.borg.vocab_app_3.room.AppDatabase;
import com.borg.vocab_app_3.room.RoomWrapper;
import com.borg.vocab_app_3.room.WordItemEntity;

import java.util.ArrayList;
import java.util.List;

public class ActiveVocabFragment extends Fragment {

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private TextView textViewWordsCount;
    private Button btnAddNewWord, btnActiveVocabMoveToInProcess, btnActiveVocabMoveToUnknown;
    private EditText editTextEnterNewWord;
    private ImageButton imgBtnDelete;
    private AppDatabase appDatabase;

    public ActiveVocabFragment() {
        super(R.layout.screen_active_vocab);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appDatabase = RoomWrapper.getDatabase(requireContext());
        imgBtnDelete = view.findViewById(R.id.imgBtnDelete);
        editTextEnterNewWord = view.findViewById(R.id.editTextEnterNewWord);
        btnAddNewWord = view.findViewById(R.id.btnAddNewWord);
        btnActiveVocabMoveToInProcess = view.findViewById(R.id.btnActiveVocabMoveToInProcess);
        btnActiveVocabMoveToUnknown = view.findViewById(R.id.btnActiveVocabMoveToUnknown);
        textViewWordsCount = view.findViewById(R.id.textViewWordsCount);
        recyclerView = view.findViewById(R.id.rvActiveVocab);

        customAdapter = new CustomAdapter(requireContext());  //??
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(customAdapter);
        getAndShowItems();

        // add new word
        btnAddNewWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < customAdapter.getItems().size(); i++) {
                    if (customAdapter.getItems().get(i).getName().equals(editTextEnterNewWord.getText().toString())) {
                        Toast.makeText(requireActivity(), "Word already exists", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                appDatabase.getAdapterItemDao().insert(new WordItemEntity(editTextEnterNewWord.getText().toString(), WordStatus.ACTIVE.ordinal()));
                getAndShowItems();
            }
        });

        // remove word
        imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < customAdapter.getItems().size(); i++) {
                    if (customAdapter.getItems().get(i).getIsSelected()) {
                        appDatabase.getAdapterItemDao().deleteById(customAdapter.getItems().get(i).getId());
                    }
                }
                getAndShowItems();
            }
        });

        // move to in process category;
        btnActiveVocabMoveToInProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (WordItem item : customAdapter.getItems()) {
                    if (item.getIsSelected()) {
                        for (int i = 0; i < customAdapter.getItems().size(); i++) {
                            if (customAdapter.getItems().get(i).getName().equals(item.getName())) {
                                Toast.makeText(requireActivity(), "Word already exists", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        appDatabase.getAdapterItemDao().update(new WordItemEntity(item.getId(), item.getName(), WordStatus.IN_PROCESS.ordinal()));
                    }
                }
                getAndShowItems();
            }
        });

        btnActiveVocabMoveToUnknown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (WordItem item : customAdapter.getItems()) {
                    if (item.getIsSelected()) {
                        for (int i = 0; i < customAdapter.getItems().size(); i++) {
                            if (customAdapter.getItems().get(i).getName().equals(item.getName())) {
                                Toast.makeText(requireActivity(), "Word already exists", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        appDatabase.getAdapterItemDao().update(new WordItemEntity(item.getId(), item.getName(), WordStatus.UNKNOWN.ordinal()));
                    }
                }
                getAndShowItems();
            }
        });
    }

    private void getAndShowItems() {

        List<WordItemEntity> itemsEntity = appDatabase.getAdapterItemDao().getAllByStatus(WordStatus.ACTIVE.ordinal());
        List<WordItem> items = new ArrayList<>();
        for (WordItemEntity itemEntity : itemsEntity) {
            WordItem item = new WordItem(itemEntity.getId(), itemEntity.getName());
            items.add(item);
        }
        textViewWordsCount.setText(items.size() + " " + "WORDS");
        customAdapter.setItems(items);
    }
}