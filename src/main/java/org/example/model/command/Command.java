package org.example.model.command;

public enum Command {
    ADD("Добавить запись в конец", true),
    OVERWRITE("Перезаписать с удалением содержимого", false);

    private final String translation;
    private final boolean recordingMode;

    Command(String translation, boolean recordingMode) {
        this.translation = translation;
        this.recordingMode = recordingMode;
    }

    public String getTranslation() {
        return translation;
    }

    public boolean getRecordingMode() {
        return recordingMode;
    }
}
