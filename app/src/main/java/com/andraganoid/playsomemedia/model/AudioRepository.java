package com.andraganoid.playsomemedia.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.andraganoid.playsomemedia.MainActivity;

import java.util.List;

public class AudioRepository {


    private AudioDao aDao;
    private LiveData <List <Audio>> rAllAudios;

    public AudioRepository(Application application) {
        PlayDatabase db = PlayDatabase.getDatabase(application);
        aDao = db.audioDao();
        rAllAudios = aDao.getAllAudios();
    }

    public LiveData <List <Audio>> getAllAudios() {
        return rAllAudios;
    }

    public void insertAudio(Audio audio) {
        new InsertAudio(aDao).execute(audio);
    }

    public void insertAudioList(List <Audio> aList, GetSomeMediaCallback callback) {
        new InsertAudioList(aDao, callback).execute(aList);

    }


    private static class InsertAudio extends AsyncTask <Audio, Void, Void> {

        private AudioDao dao;

        InsertAudio(AudioDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(final Audio... params) {
            dao.insert(params[0]);
            return null;
        }
    }

    private static class InsertAudioList extends AsyncTask <List <Audio>, Void, Void> {

        private AudioDao dao;
        private GetSomeMediaCallback callback;

        InsertAudioList(AudioDao dao, GetSomeMediaCallback callback) {
            this.dao = dao;
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(final List <Audio>... params) {
            dao.deleteAllAudio();
            for (Audio a : params[0]) {
                dao.insert(a);
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
