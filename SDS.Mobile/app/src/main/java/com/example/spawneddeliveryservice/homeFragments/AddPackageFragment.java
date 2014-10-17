package com.example.spawneddeliveryservice.homeFragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;

import com.example.spawneddeliveryservice.HomeActivity;
import com.example.spawneddeliveryservice.R;
import com.example.spawneddeliveryservice.appData.DAO;
import com.example.spawneddeliveryservice.models.NewPackageDataModel;
import com.example.spawneddeliveryservice.models.Town;
import com.example.spawneddeliveryservice.tasks.CreatePackageTask;

import java.util.ArrayList;
import java.util.List;

public class AddPackageFragment extends Fragment implements View.OnClickListener {
    Button btnImage, btnAdditionalContact, addPackage;
    EditText etPrice, etSpace, etKilograms, etNotes;
    EditText etDeadline;
    Spinner spFrom, spTo;
    private Context context;
    private GridView gvFinishedPackages;

    public static String phoneNumber = "";
    public static String base64Image= "";

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
        this.etDeadline = (EditText) rootView.findViewById(R.id.etTransportsNewTransportDeadline);
        this.etNotes = (EditText) rootView.findViewById(R.id.etTransportsNewTransportNotes);

        this.spFrom = (Spinner) rootView.findViewById(R.id.spTransportsNewTransportFrom);
        this.spTo = (Spinner) rootView.findViewById(R.id.spTransportsNewTransportTo);

        this.loadTowns();

        return rootView;
    }

    public void loadTowns() {
        List<Town> towns = DAO.getTowns(this.context);
        ArrayList<String> townNames = new ArrayList<String>();
        for (int i = 0; i < towns.size(); i++) {
            townNames.add(towns.get(i).getName());
        };

        ArrayAdapter<String> adaptorNames = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_dropdown_item, townNames);
        this.spFrom.setAdapter(adaptorNames);
        this.spTo.setAdapter(adaptorNames);
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
        List<Town> towns = DAO.getTowns(context);

        NewPackageDataModel model = new NewPackageDataModel(base64Image, Double.parseDouble(this.etPrice.getText().toString()),
                Integer.parseInt(this.etSpace.getText().toString()), Integer.parseInt(this.etKilograms.getText().toString()),
                towns.get(this.spFrom.getSelectedItemPosition()).getName(), towns.get(this.spTo.getSelectedItemPosition()).getName(),
                this.etDeadline.getText().toString(), this.etNotes.getText().toString(), phoneNumber);

        (new CreatePackageTask(context)).execute(model);
    }



    private void addContact() {
        ((HomeActivity)context).addContact();
    }

    private void takeAPicture() {
        ((HomeActivity)context).takePicture();
    }
}
