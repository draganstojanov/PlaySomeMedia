package com.andraganoid.playsomemedia.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import java.util.List;

public class VideoRepository {

    private VideoDao vDao;
    private LiveData <List <Video>> rAllVideos;

    public VideoRepository(Application application) {
        PlayDatabase db = PlayDatabase.getDatabase(application);
        vDao = db.videoDao();
        rAllVideos = vDao.getAllVideos();
    }

    public LiveData <List <Video>> getAllVideos() {
        return rAllVideos;
    }

    public void insertVideo(Video video) {
        new InsertVideo(vDao).execute(video);
    }

    public void insertVideoList(List <Video> vList) {
        new InsertVideoList(vDao).execute(vList);

    }


    private static class InsertVideo extends AsyncTask <Video, Void, Void> {

        private VideoDao dao;

        InsertVideo(VideoDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(final Video... params) {
            dao.insert(params[0]);
            return null;
        }
    }

    private static class InsertVideoList extends AsyncTask <List <Video>, Void, Void> {

        private VideoDao dao;

        InsertVideoList(VideoDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(final List <Video>... params) {
            dao.deleteAllVIdeo();
            for (Video v : params[0]) {
                dao.insert(v);
            }
            return null;
        }
    }

}
