package org.example.model.command;

public enum CommandsEnum {
    ADD("Добавить запись в конец", true),
    OVERWRITE("Перезаписать с удалением содержимого", false);

    private final String translation;
    private final boolean recordingMode;

    CommandsEnum(String translation, boolean recordingMode) {
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
