# Příklad Capri Konektoru v Javě

Tento projekt je Java Maven příklad jednoduché implementace klienta pro webové služby Capri (SOAP).

## Instalace

1.  **Získejte Přístup:**
    Nejprve kontaktujte Prins Software ([https://prins.cz](https://prins.cz)) a požádejte o přístup k jejich webovým službám. Pokud již přístup máte, můžete tento krok přeskočit.

2.  **Nainstalujte Předpoklady:**
    *   Nainstalujte JDK 21.
    *   Nainstalujte Maven 3.x.

3.  **Připravte Certifikát:**
    *   Zkopírujte váš soubor s certifikátem `.p12` do této složky a přejmenujte ho na `cc-key.p12`.

4.  **Stáhněte Soubor WSDL:**
    *   Spusťte následující příkaz `curl` ke stažení souboru WSDL:
        ```bash
        curl -L --cert ./cc-key.p12:HESLO --cert-type P12 -o capri-connector.wsdl https://secure.prins.cz:8443/CapriConnector?wsdl
        ```

5.  **Aktualizujte Přihlašovací Údaje:**
    *   Otevřete soubor [Main.java](./src/main/java/cz/prins/capriconnector/client/example/Main.java) a vyplňte vaše přihlašovací údaje tam, kde je uvedeno `//TODO`.

6.  **Sestavte Projekt:**
    *   Spusťte následující příkaz k sestavení projektu:
        ```bash
        mvn install
        ```

7.  **Spusťte Příklad:**
    *   Spusťte následující příkaz ke spuštění příkladu:
        ```bash
        mvn exec:java -Dexec.mainClass=cz.prins.capriconnector.client.example.Main
        ```
