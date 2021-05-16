package com.example.demo.models;

public class BotRequest {
    private String value;
    private boolean generateAudio;
    private String audioUrl;

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public boolean isGenerateAudio() {
        return generateAudio;
    }
    public void setGenerateAudio(boolean generateAudio) {
        this.generateAudio = generateAudio;
    }
    public String getAudioUrl() {
        return audioUrl;
    }
    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
}
