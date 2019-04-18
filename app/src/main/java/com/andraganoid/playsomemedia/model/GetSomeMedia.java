package com.andraganoid.playsomemedia.model;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;


public class GetSomeMedia {

   private Context context;
   private GetSomeMediaCallback callback;

    public GetSomeMedia(Context context, GetSomeMediaCallback callback) {
        this.context = context;
        this.callback = callback;
        getSomeVideo();
        getSomeAudio();
    }




    private void getSomeVideo() {

        List<Video> getVideo=new ArrayList <>();
        Cursor vCursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        try {
            vCursor.moveToFirst();
            do {
                getVideo.add(new Video(
                        vCursor.getString(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)),
                        vCursor.getString(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)),
                        vCursor.getString(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)),
                        vCursor.getLong(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_TAKEN)),
                        vCursor.getLong(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)),
                        vCursor.getInt(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH)),
                        vCursor.getInt(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT )),
                        vCursor.getString(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.RESOLUTION))
                ));


//                System.out.println("VIDEO-MINI_THUMB_MAGIC "+(vCursor.getString(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.MINI_THUMB_MAGIC))));
//                System.out.println("VIDEO-RESOLUTION "+(vCursor.getString(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.))));
//                System.out.println("VIDEO-DISPLAY_NAME "+(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME))));
//                System.out.println("VIDEO-DATE_TAKEN "+(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_TAKEN))));
//                System.out.println("VIDEO-DURATION "+(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION))));
//                System.out.println("VIDEO-WIDTH "+(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH))));
//                System.out.println("VIDEO-HEIGHT "+(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT))));
//
//                System.out.println("VIDEO-SIZE "+(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE))));
//                System.out.println("VIDEO-TITLE "+(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE))));


            } while (vCursor.moveToNext());

            vCursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        callback.videoListCollected(getVideo);
    }


   private void getSomeAudio() {
       List<Audio> getAudio=new ArrayList <>();
       Cursor aCursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
       try {
           aCursor.moveToFirst();
           do {
               getAudio.add(new Audio(
                       aCursor.getString(aCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)),
                       aCursor.getString(aCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)),
                       aCursor.getString(aCursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)),
                       aCursor.getString(aCursor.getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST)),
                       aCursor.getLong(aCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION))

               ));
      /*         System.out.println("VIDEO-DATA "+(aCursor.getString(aCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))));
               System.out.println("VIDEO-DISPLAY_NAME "+(aCursor.getString(aCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME))));
               System.out.println("VIDEO-DURATION "+(aCursor.getString(aCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))));
               System.out.println("VIDEO-TITLE "+(aCursor.getString(aCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))));
               System.out.println("VIDEO-SIZE "+(aCursor.getString(aCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE))));
               System.out.println("VIDEO-WIDTH "+(aCursor.getString(aCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.WIDTH))));
               System.out.println("VIDEO-HEIGHT "+(aCursor.getString(aCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.HEIGHT))));

               System.out.println("VIDEO-ARTIST "+(aCursor.getString(aCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))));

               System.out.println("VIDEO-RECORD_SOUND_ACTION "+(aCursor.getString(aCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.RECORD_SOUND_ACTION))));



*/
           } while (aCursor.moveToNext());

           aCursor.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
      callback.audioListCollected(getAudio);
    }


}
