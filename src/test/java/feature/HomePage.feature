Feature: Trendyol Home

  @trendyolHome
  Scenario: Trendyol Home Page Writing Response Code of Boutiques Links Into The Csv File
    * 'https://www.trendyol.com/' ekrani acilir
    * gelen popup kapatilir
    * butik linkleri alinir
    * butik linkleri ve status codeları csv dosyaya yazilir