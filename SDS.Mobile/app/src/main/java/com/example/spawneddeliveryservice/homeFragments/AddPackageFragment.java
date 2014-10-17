package com.example.spawneddeliveryservice.homeFragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;

import com.example.spawneddeliveryservice.R;
import com.example.spawneddeliveryservice.appData.DAO;
import com.example.spawneddeliveryservice.models.Town;

import java.util.List;

public class AddPackageFragment extends Fragment implements View.OnClickListener {
    Button btnImage, btnAdditionalContact, addPackage;
    EditText etPrice, etSpace, etKilograms, etNotes;
    DatePicker etDeadline;
    Spinner spFrom, spTo;
    private Context context;
    private GridView gvFinishedPackages;

    public AddPackageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_packages_add_package, container, false);

        this.context = container.getContext();
        this.btnImage = (Button) rootView.findViewById(R.id.btnTransportsNewTransportTakeAPicture);
        this.btnImage.setOnClickListener(this);
        this.btnAdditionalContact = (Button) rootView.findViewById(R.id.btnTransportsNewTransportAdditionalContacts);
        this.btnAdditionalContact.setOnClickListener(this);
        this.addPackage = (Button) rootView.findViewById(R.id.btnPackagesAddPackage);
        this.addPackage.setOnClickListener(this);

        this.etPrice = (EditText) rootView.findViewById(R.id.etTransportsNewTransportPrice);
        this.etSpace = (EditText) rootView.findViewById(R.id.etTransportsNewTransportSpace);
        this.etKilograms = (EditText) rootView.findViewById(R.id.etTransportsNewTransportKilograms);
        this.etDeadline = (DatePicker) rootView.findViewById(R.id.etTransportsNewTransportDeadline);
        this.etNotes = (EditText) rootView.findViewById(R.id.etTransportsNewTransportNotes);

        this.spFrom = (Spinner) rootView.findViewById(R.id.spTransportsNewTransportFrom);
        this.spTo = (Spinner) rootView.findViewById(R.id.spTransportsNewTransportTo);

        this.loadTowns();

        return rootView;
    }

    public void loadTowns() {
        List<Town> towns = DAO.getTowns(this.context);
//        ArrayAdapter<Town> adapter = new ArrayAdapter<Town>(this.context,R.layout.simple_spinner_item,towns);
//        mSpinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPackagesAddPackage) {
            this.addPackage();
        } else if (v.getId() == R.id.btnTransportsNewTransportAdditionalContacts) {
            this.addContact();
        } else if (v.getId() == R.id.btnTransportsNewTransportTakeAPicture) {
            this.takeAPicture();
        }
    }

    private void addPackage() {
        //Logic to add package
    }

    private void addContact() {
        //Logic to add contact to form
    }

    private void takeAPicture() {
    }
}
