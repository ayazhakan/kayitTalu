package com.guvenlikKayit.TaluKayit.ui;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends Composite<LoginOverlay> {

    public LoginView(){
        getContent().setTitle("Talu Tekstil Arac Takip Kayit");
        getContent().setOpened(true);
        getContent().setError(false);
        getContent().setAction("login");
        getContent().setDescription("");


        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setAdditionalInformation(
                "Contact Hakan AYAZ if you're experiencing issues logging into your account");
        getContent().setI18n(i18n);
        getContent().setForgotPasswordButtonVisible(false);

    }

}
