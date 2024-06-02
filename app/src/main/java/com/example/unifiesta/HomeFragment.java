package com.example.unifiesta;

import android.os.Bundle;
import android.os.Bundle;

import android.view.View;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ArrayList<CourseModal> courseModalArrayList;
    private DBHandler dbHandler;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the image slider
        List<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.football,  ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.dj_image,  ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.images, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.dance, ScaleTypes.CENTER_CROP));

        ImageSlider imageSlider = mView.findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);
        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(requireContext());

        // getting our course array list from db handler class.
        courseModalArrayList = dbHandler.readCourses();

        // on below line passing our array list to our adapter class.
        courseRVAdapter = new CourseRVAdapter(courseModalArrayList, requireContext());
        coursesRV = mView.findViewById(R.id.idevents3);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);


        return mView;
    }
}



