# Trello API Test Projesi

Bu proje, Trello API'sini kullanarak temel Trello işlemlerini gerçekleştiren bir test senaryosu içerir.

## Proje hakkında

Bu proje, "RestAssured" Kütüphanesini kullanarak Trello API'sini test etmek için oluşturuldu. Temel Trello işlemlerini içerir (pano oluşturma, liste oluşturma, kart oluşturma, kart adını güncelleme, kartları silme, panoyu silme).

Tüm işlemlerden sonra ResponseBodies’i kontrol eder ve başarılı olursa gerekli logları hem konsola hem de log klasörü altındaki mylog.log’a yazdırır.

## Nasıl kullanılır

1. **API Anahtarı ve Token Alımı:**
   Proje, Trello API'sini kullanmak için geçerli bir API anahtarı ve belirteci gerektiriyor. Bu bilgiyi [Trello Developer'dan](https://developer.atlassian.com/cloud/trello/rest/) alabilirsiniz.

2. **Maven Bağımlılıklarını Yükleme:**
   Proje bağımlılıkları POM.xml de yer almaktadır.


3. **TrelloTest Sınıfını Çalıştırma:**
Proje ana dizininde bulunan TrelloTest sınıfını çalıştırarak test senaryosunu başlatabilirsiniz.


## Proje Yapısı
- **trelloApi/TrelloBoard:** Trello ile ilgili işlemleri gerçekleştiren sınıf.
- **trelloTest/TrelloTest:** JUnit test sınıfı, TrelloBoard sınıfını test etmek için yazılmıştır.
- **trelloTest/TrelloConfig:** Gerekli konfigürasyon işlemlerinin yapıldırı sınıfı temsil eder.
- **utils/Logger:** log işlemlerini gerçekleştiren yardımcı sınıf.

### Test Senaryosu Aşamaları
1. **Pano Oluşturma:**
TrelloBoard sınıfı, belirtilen ada sahip bir Trello panosu oluşturur.

2. **Liste Oluşturma:**
Oluşturulan panoda belirtilen adla bir Trello listesi oluşturur.

3. **Pano Oluşturma:**
Oluşturulan listede belirtilen iki adla iki Trello kartı oluşturur.

4. **Kart Adını Güncelleyin:**
Rastgele bir kartın adını günceller.

5. **Kartları Sil:**
Oluşturulan kartları siler.

6. **Pano Silme:**
Oluşturulan panoyu siler.

### Notlar
- Projeyi çalıştırmadan önce geçerli bir "Trello API_KEY" ve "TOKEN" edinmelisiniz.
