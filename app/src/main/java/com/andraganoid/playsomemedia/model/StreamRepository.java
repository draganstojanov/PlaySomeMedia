package com.andraganoid.playsomemedia.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StreamRepository {

    private StreamDao sDao;
    private LiveData <List <Stream>> rAllStreams;

    public StreamRepository(Application application) {
        PlayDatabase db = PlayDatabase.getDatabase(application);
        sDao = db.streamDao();
        rAllStreams = sDao.getAllStreams();
    }

    public LiveData <List <Stream>> getAllStreams() {
        return rAllStreams;
    }

    public void insertStream(Stream stream) {
        new InsertStream(sDao).execute(stream);
    }

    public void insertStreamList(List <Stream> sList, GetSomeMediaCallback callback) {
       new InsertStreamList(sDao, callback).execute(sList);
    }


    private static class InsertStream extends AsyncTask <Stream, Void, Void> {

        private StreamDao dao;

        InsertStream(StreamDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(final Stream... params) {
            dao.insert(params[0]);
            return null;
        }
    }

    private static class InsertStreamList extends AsyncTask <List <Stream>, Void, Void> {

        private StreamDao dao;
        private GetSomeMediaCallback callback;

        InsertStreamList(StreamDao dao, GetSomeMediaCallback callback) {
            this.dao = dao;
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(final List <Stream>... params) {
            dao.deleteAllStreams();
            for (Stream s : params[0]) {
                dao.insert(s);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            callback.taskFinished();

        }
    }

}
