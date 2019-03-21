package com.epam.kotlinkoans.i_introduction._7_Nullable_Types;

import com.epam.kotlinkoans.util.JavaCode;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import i_introduction._7_Nullable_Types.Client;
import i_introduction._7_Nullable_Types.Mailer;
import i_introduction._7_Nullable_Types.PersonalInfo;

public class JavaCode7 extends JavaCode {
    public void sendMessageToClient(@Nullable Client client, @Nullable String message, @NotNull Mailer mailer) {
        if (client == null || message == null) return;

        PersonalInfo personalInfo = client.getPersonalInfo();
        if (personalInfo == null) return;

        String email = personalInfo.getEmail();
        if (email == null) return;

        mailer.sendMessage(email, message);
    }
}
