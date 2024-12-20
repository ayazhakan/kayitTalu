package com.guvenlikKayit.TaluKayit.ui;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.Route;

import javax.servlet.annotation.WebServlet;

@WebServlet("/login")
@Route("/login")  // /login yoluna gelen istekler burada karşılanır
public class LoginView extends Composite<LoginOverlay> {

    public LoginView() {
        // LoginOverlay sayfasının başlık ve özelliklerini ayarlıyoruz
        getContent().setTitle("Talu Tekstil Arac Takip Kayit");
        getContent().setOpened(true);  // Overlay'i açıyoruz
        getContent().setError(false);  // Hata durumunu false yapıyoruz
        getContent().setAction("login");  // Formun 'action' kısmını 'login' olarak belirliyoruz

        // I18n (Uluslararasılaştırma) ayarları
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setAdditionalInformation("Contact Hakan AYAZ if you're experiencing issues logging into your account");
        getContent().setI18n(i18n);
        getContent().setForgotPasswordButtonVisible(false);  // Şifre unuttum butonunu gizliyoruz
    }
}
