# WebCrawler ![WebCrawler is a java project](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
## Descrizione
### Cosa fa?
L'applicazione ***WebCrawler*** si occupa di scaricare tutte le immagini presenti in una pagina web fornita dall'utente.

### Chi la può usare?
Solo gli utenti registrati possono utilizzare l'applicazione, e tutti i nuovi utenti hanno a disposizione 5 download gratuiti, dopo per ottenere altri download potranno contattare l'admin che potrà modificare il limite di ogni singolo utente.
Un utente può registrarsi nell'apposito menù inserendo un username e una password. (*I dati verranno inseriti in un database, perciò la password prima di essere salvata verrà automaticamente criptata con l'algoritmo di hashing **MD5***)

### Admin
L'admin è il gestore del servizio che l'applicazione offre, esso può controllare quali utenti hanno richiesto un aumento del limite di download, decidendo poi se modificarlo, aumentandolo o diminuendolo, oppure declinare la richiesta.
Per accedere all'admin panel sarà necessario disporre della  password.
___
# Installazione
### Variabili d'ambiente
Nel programma sono presenti 5 variabili d'ambiente:

 - ***ADMIN_CRAWLER_KEY***: contenente la password necessaria per accedere all'admin panel;
 - ***DATABASE_HOST***: contenente il server sul quale risiede il database. Nel caso in cui il programma e il database siano sulla stessa macchina sarà "localhost", altrimenti sarà l'indirizzo IP del server esterno;
  - ***DATABASE_PORT***: contenente il numero di porta (la porta di default è la 3306, ma se su quella porta è già presente un database può variare);
 - ***DATABASE_USERNAME***: contenente l'username per l'accesso al database;
 - ***DATABASE_PASSWORD***: contenente la password per l'accesso al database;
 ### DataBase
Dal momento che il programma utilizza un database per il salvataggio dei dati, è necessario importare il database web_crawler, con le relative tabelle, dallo script.
Lo script è contenuto nella cartella "sql" del progetto con il nome di "init.sql" e il database è di tipo "MySQL"