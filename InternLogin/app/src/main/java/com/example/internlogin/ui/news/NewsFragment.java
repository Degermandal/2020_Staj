package com.example.internlogin.ui.news;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.internlogin.Adapter.NewsAdapter;
import com.example.internlogin.Model.News;
import com.example.internlogin.R;
import com.example.internlogin.api.ApiUtils;
import com.example.internlogin.interfaced.APIService;
import com.example.internlogin.modelOfResponse.GetNews.GetNews;
import com.example.internlogin.ui.menu.MenuFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {

    List<News> newsList;
    NewsAdapter newsAdapter;
    ListView listView;
    APIService apiService;
    ListView lv_news;
    View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_news, container, false);


        apiService = ApiUtils.getNewsService();
        listView = root.findViewById(R.id.lv_news);
        newsList = new ArrayList<>();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Haber kanalına yönlendiriyor.
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsList.get(i).getUrl()));
                startActivity(browserIntent);
            }
        });


        clickBack();
        getNews();

        return root;
    }

    private void clickBack() {
        root.findViewById(R.id.btn_back_news).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment menuFragment = new MenuFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,menuFragment)
                        .commit();
            }
        });
    }

    private void getNews() {
        apiService.getFinanceNews().enqueue(new Callback<GetNews>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<GetNews> call, Response<GetNews> response) {
                if(response.isSuccessful()) {
                    if(response.body() !=  null) {
                        if(response.body().getArticles() != null) {
                            System.out.println(response.body().getArticles().get(0).getTitle());
                            response.body().getArticles().stream().forEach(o -> newsList.add(new News(o.getTitle(), o.getUrl())));

                            newsAdapter = new NewsAdapter(newsList, getContext());
                            listView.setAdapter(newsAdapter);
                        }
                        else
                            System.out.println("null ");
                    } else
                        System.out.println("Get News : Response Body Null");
                }
            }

            @Override
            public void onFailure(Call<GetNews> call, Throwable t) {
                System.out.println("Get News Fail : " + t.getMessage());
            }
        });
    }
}