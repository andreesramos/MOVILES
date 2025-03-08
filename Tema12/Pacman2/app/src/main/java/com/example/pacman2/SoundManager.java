package com.example.pacman2;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.AudioAttributes;
import android.util.Log;

import java.util.HashMap;

public class SoundManager {
    private static MediaPlayer backgroundMusic;
    private static SoundPool soundPool;
    private static HashMap<String, Integer> soundMap = new HashMap<>();

    public static void initSounds(Context context) {
        // Configurar SoundPool
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(attributes)
                .build();

        // Cargar efectos de sonido
        soundMap.put("comer_fantasma", soundPool.load(context, R.raw.fantasma, 1));
        soundMap.put("muerte", soundPool.load(context, R.raw.muerte, 1));
        soundMap.put("power_up", soundPool.load(context, R.raw.power, 1));
        soundMap.put("victoria", soundPool.load(context, R.raw.victoria, 1));

        // Iniciar música de fondo
        backgroundMusic = MediaPlayer.create(context, R.raw.music);
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f, 0.5f);
    }

    public static void playSound(String soundName) {
        Integer soundId = soundMap.get(soundName);
        if (soundId != null) {
            soundPool.play(soundId, 1, 1, 1, 0, 1);
        } else {
            Log.e("SoundManager", "Sonido no encontrado: " + soundName);
        }
    }

    public static void playBackgroundMusic() {
        if (backgroundMusic != null && !backgroundMusic.isPlaying()) {
            backgroundMusic.start();
        }
    }

    public static void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isPlaying()) {
            backgroundMusic.pause();
        }
    }

    public static void release() {
        if (backgroundMusic != null) {
            backgroundMusic.release();
            backgroundMusic = null;
        }
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}
