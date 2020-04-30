package dk.aau.student.meda2a220a.p2protype;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Objects;

public class AddFriendDialog extends AppCompatDialogFragment {

        private EditText editName;

        private AddFriendListener addFriendListener;

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder logBuilder = new AlertDialog.Builder(getActivity());

            LayoutInflater logInflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
            View inflatedFriendView = logInflater.inflate(R.layout.addfrienddialog, null);

            logBuilder.setView(inflatedFriendView)
                    .setTitle("Enter Username")
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String userNameString = editName.getText().toString();


                            addFriendListener.applyAddition(userNameString); //Applying user-defined strings from dialog window to applyAddition-method in Social-class

                        }
                    });

            editName = inflatedFriendView.findViewById(R.id.friendNameEditText);



            return logBuilder.create();


        }

        @Override
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);

            try {
                addFriendListener = (AddFriendListener) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() + "Must implement AddFriendListener"); //If not implemented in class
            }
        }

        public interface AddFriendListener {
            void applyAddition(String userNameString); //Override in Social-Class


        }



    }




