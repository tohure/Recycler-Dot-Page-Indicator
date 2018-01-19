package io.tohure.recyclerdotpageindicator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.tohure.dotpageindicator.DotPageIndicator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        RecyclerView rvGallery = findViewById(R.id.rvGallery);
        rvGallery.setHasFixedSize(true);

        final GalleryAdapter galleryAdapter = new GalleryAdapter();
        rvGallery.setAdapter(galleryAdapter);
        final DotPageIndicator dotPageIndicator = findViewById(R.id.dpiIndicator);
        dotPageIndicator.attachToRecyclerView(rvGallery);

        List<String> listImages = new ArrayList<>();

        listImages.add("https://okdiario.com/img/2017/06/13/are-7878_slide18b_mr-1115-e1497357991434-655x368.jpg");
        listImages.add("https://cdni.rt.com/actualidad/public_images/fe4/fe4d5c509a9c17d5c30205d4603746a2_article.jpg");
        listImages.add("https://i.ytimg.com/vi/DuK2T-lNBT0/maxresdefault.jpg");
        listImages.add("https://www.cronista.com/__export/1510340950718/sites/diarioelcronista/img/2017/11/10/defensa.jpg_258117318.jpg");
        listImages.add("https://i.pinimg.com/736x/2b/0b/ea/2b0beabf9d19fdf2134ba3b5fc3a34e9--tv-mac-desktop.jpg");
        listImages.add("https://cdni.rt.com/actualidad/public_images/e51/e51aa1e1fb1a60deb1a284b64db269e3_article.jpg");
        listImages.add("https://www.hibridosyelectricos.com/media/hibridos/images/2016/02/04/2016020413260053213.jpg");
        listImages.add("https://i.ytimg.com/vi/XBW8K2ItBag/hqdefault.jpg");
        listImages.add("http://img.microsiervos.com/images2017/AbsurdPlanes1.jpg");
        listImages.add("https://static.iris.net.co/dinero/upload/images/2016/10/20/235104_1.jpg");

        galleryAdapter.addData(listImages);

    }

}