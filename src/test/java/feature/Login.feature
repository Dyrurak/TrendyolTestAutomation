Feature: Trendyol Login


  Background:
    * 'https://www.trendyol.com/login' ekrani acilir

  Scenario: Trendyol Successful Login
    * 'CustomerCorrectEmailCorrectPassword' tipteki kullanıcının bilgileri cekilir
    * Email alanina email girilir
    * Sifre alanina sifre girilir
    * Giris yap butonuna tiklanir
    * Basarili giris yapildigi gorulur

  Scenario: Trendyol Unsuccessful Login With Correct Email And Wrong Password
    * 'CustomerCorrectEmailWrongPassword' tipteki kullanıcının bilgileri cekilir
    * Email alanina email girilir
    * Sifre alanina sifre girilir
    * Giris yap butonuna tiklanir
    * 'E-posta adresiniz ve/veya şifreniz hatalı.' hata mesaji gorulur

  Scenario: Trendyol Unsuccessful Login With Wrong Email And Correct Password
    * 'CustomerWrongEmailCorrectPassword' tipteki kullanıcının bilgileri cekilir
    * Email alanina email girilir
    * Sifre alanina sifre girilir
    * Giris yap butonuna tiklanir
    * 'E-posta adresiniz ve/veya şifreniz hatalı.' hata mesaji gorulur

  Scenario: Trendyol Unsuccessful Login With Empty Email And Empty Password
    * Giris yap butonuna tiklanir
    * 'Lütfen geçerli bir e-posta adresi giriniz.' hata mesaji gorulur

  Scenario: Trendyol Unsuccessful Login With Correct Email And Empty Password
    * 'CustomerCorrectEmailWrongPassword' tipteki kullanıcının bilgileri cekilir
    * Email alanina email girilir
    * Giris yap butonuna tiklanir
    * 'Lütfen şifrenizi giriniz.' hata mesaji gorulur

  Scenario: Trendyol Unsuccessful Login With Empty Email And Correct Password
    * 'CustomerCorrectEmailCorrectPassword' tipteki kullanıcının bilgileri cekilir
    * Sifre alanina sifre girilir
    * Giris yap butonuna tiklanir
    * 'Lütfen geçerli bir e-posta adresi giriniz.' hata mesaji gorulur

  Scenario: Trendyol Sending Reset Password Successfully
    * 'CustomerForgotPasswordCorrectEmail' tipteki kullanıcının bilgileri cekilir
    * sifremi unuttuma tiklanir
    * eposta alanina eposta yazilir
    * sifremi yenile butonuna tiklanir
    * 'Şifreniz Gönderildi' yazisi gorulur

  Scenario: Trendyol Successful Login Via Facebook
    * 'CustomerFacebook' tipteki kullanıcının bilgileri cekilir
    * facebook ile giris yap butonuna tiklanir
    * email alanina facebook email girilir
    * password alanina facebook sifre girilir
    * facebook giris yap butonuna tiklanir
    * Basarili giris yapildigi gorulur