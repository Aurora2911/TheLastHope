package com.adventure.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Creates (if it doesn't exists) and initializes the database used.
 */
public class DbInitAdventure extends H2Utility {
        public void initAndCheck(String readFromDbQuery, String insertValuesQuery, List<String> paramListRec,
                        int columnsNumRec) throws SQLException {
                ResultSet result = readFromDb(readFromDbQuery);

                if (isExisted(result, paramListRec.get(0)) == false)
                        insertStringIntoDbTable(insertValuesQuery, paramListRec, columnsNumRec);

        }

        public void initDb() throws SQLException {
                List<String> paramList = new ArrayList<>();
                Properties dbProp = new Properties();
                final String pathDb = "jdbc:h2:./MAP/db/prova";
                int columnsNum = 2;

                // Intro
                String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Cutscene (Descr VARCHAR(1024) PRIMARY KEY, Text VARCHAR(10000))";
                String INSERT_INTO_TABLE = "INSERT INTO Cutscene VALUES (?,?)";
                String READ_FROM_DB = "SELECT Descr FROM Cutscene WHERE Descr = 'Intro'";

                dbProp.setProperty("user", "user");
                dbProp.setProperty("password", "1234");

                openDbConnection(pathDb, dbProp);
                createDbTable(CREATE_TABLE);

                paramList.add("Intro");
                paramList.add("Luglio 1990. L'Ucraina dichiara di voler diventare un Paese indipendente con la Dichiarazione di sovranità dell'Ucraina <br><br>"
                                +
                                "Febbraio 2014. Ucraina. Eserciti russi giungono in Crimea. I rapporti diplomatici tra i due Paesi sono ormai insanabili <br><br>"
                                +
                                "Marzo 2021. 100.000 soldati russi sono schierati al confine ucraino, per impedire che il Paese entri definitivamente <br>a far parte della NATO.<br><br>"
                                +
                                "Febbraio 2022. Il presidente Vladimir Putin annuncia l'inizio delle operazioni militari russe che porteranno <br>all'invasione dell'Ucraina e alla definitiva rottura tra Oriente e Occidente.<br>"
                                +
                                "<br>La Russia ha intenzione di porre fine a questa storia durata fin troppo e utilizzare l'armata nucleare per vincere <br>questa guerra."
                                +
                                "Sarai tu colui che impedirà che questo accada. Il tuo comandante è stato catturato e sarà giustiziato per le sue conoscenze considerate rischiose per i russi.<br>"
                                +
                                "<br>Soldato, la tua prima missione è trovarlo e liberarlo. Rendici fieri. Salva l'umanità da quella che altrimenti sarebbe la sua fine!<br>");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                // Outro
                READ_FROM_DB = "SELECT Descr FROM Cutscene WHERE descr = 'Outro'";
                paramList.clear();
                paramList.add("Outro");
                paramList.add("FINE DELLA DEMO<br><br>Complimenti soldato, hai salvato il comandante da una morte certa! <br><br> Ma non è finita qui. I russi complottano qualcosa, qualcosa di grosso <br><br>Grazie per aver giocato <br><br> Continua...");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT Descr FROM Cutscene WHERE descr = 'SpecialOutro'";
                paramList.clear();
                paramList.add("SpecialOutro");
                paramList.add("FINALE SEGRETO<br><br> Hai recuperato informazioni TOP SECRET utili per incastrare Putin"
                                + "<br><br>Grazie a te la guerra rimarrà solo un brutto ricordo <br>Grazie per aver giocato <br><br>Fine");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                // MainCharacter reflections
                CREATE_TABLE = "CREATE TABLE IF NOT EXISTS MainCharacterReflections (CharacterMoment VARCHAR(1024) PRIMARY KEY, Text VARCHAR(10000))";
                INSERT_INTO_TABLE = "INSERT INTO MainCharacterReflections VALUES (?,?)";

                createDbTable(CREATE_TABLE);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterReflections WHERE CharacterMoment = 'REF[0]'";
                paramList.clear();
                paramList.add("REF[0]");
                paramList.add("-Ho infuocato la stanza. Molto probabilmente le guardie che sorvegliavano il comandante si accorgeranno dell'incendio.\n"
                                + "Nel garage hanno un'automobile. Non ci penseranno due volte a fuggire con essa. "
                                + "Forse ieri hanno esagerato con la vodka e potrebbero facilitarmi la missione!\n"
                                + "Devo andare via da qui!");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterReflections WHERE CharacterMoment = 'REF[1]'";
                paramList.clear();
                paramList.add("REF[1]");
                paramList.add("-Queste urla provengono dal seminterrato! È sicuramente lì che è tenuto prigioniero.\n Devo trovare il modo di liberarmi delle guardie.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterReflections WHERE CharacterMoment = 'REF[2]'";
                paramList.clear();
                paramList.add("REF[2]");
                paramList.add("-Bene, sono dentro. Devo sbrigarmi. *si traveste*\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterReflections WHERE CharacterMoment = 'REF[2.1]'";
                paramList.clear();
                paramList.add("REF[2.1]");
                paramList.add("-Non mi stanno poi così male.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterReflections WHERE CharacterMoment = 'REF[3]'";
                paramList.clear();
                paramList.add("REF[3]");
                paramList.add("-Sta un soldato. Devo inventarmi qualcosa.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterReflections WHERE CharacterMoment = 'REF[4]'";
                paramList.clear();
                paramList.add("REF[4]");
                paramList.add("-Devo trovare il modo per ingannarlo. Forse un po' di sonno gli farebbe bene.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterReflections WHERE CharacterMoment = 'REF[5]'";
                paramList.clear();
                paramList.add("REF[5]");
                paramList.add("-Trovata! Servirà sicuramente per entrare nella stanza dell'ufficiale.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterReflections WHERE CharacterMoment = 'REF[6]'";
                paramList.clear();
                paramList.add("REF[6]");
                paramList.add("-E' una combinazione di numeri. Sarà per una delle casseforti di cui parlava il comandante? Devo controllare qui in giro.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterReflections WHERE CharacterMoment = 'REF[7]'";
                paramList.clear();
                paramList.add("REF[7]");
                paramList.add("-Bel quadro. Sicuramente la cassaforte sarà qui dietro.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterReflections WHERE CharacterMoment = 'REF[8]'";
                paramList.clear();
                paramList.add("REF[8]");
                paramList.add("-Come nei film! Adesso serve la combinazione.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterReflections WHERE CharacterMoment = 'REF[9]'";
                paramList.clear();
                paramList.add("REF[9]");
                paramList.add("-E' fatta! Devo solo trovare un modo per superare le guardie.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterReflections WHERE CharacterMoment = 'REF[10]'";
                paramList.clear();
                paramList.add("REF[10]");
                paramList.add("-E' arrivato il momento.\n");

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterReflections WHERE CharacterMoment = 'REF[11]'";
                paramList.clear();
                paramList.add("REF[11]");
                paramList.add("-Una password?! Non me ne avevano parlato! Dovrò sbrigarmela da solo.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterReflections WHERE CharacterMoment = 'REF[12]'";
                paramList.clear();
                paramList.add("REF[12]");
                paramList.add("-Sono proprio contento di essere vivo. Tutto d'un pezzo, prossimo al congedo. Certo, vivo in un mondo di merda, questo sì. Ma sono vivo. E non ho più paura.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterReflections WHERE CharacterMoment = 'Benzina'";
                paramList.clear();
                paramList.add("Benzina");
                paramList.add("-Devo consumare la benzina quando costa così tanto!\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterReflections WHERE CharacterMoment = 'Cassaforte'";
                paramList.clear();
                paramList.add("Cassaforte");
                paramList.add("-Magari fosse stato così semplice!\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                // MainCharacter Dialogs
                CREATE_TABLE = "CREATE TABLE IF NOT EXISTS MainCharacterDialogs (CharacterMoment VARCHAR(1024) PRIMARY KEY, Text VARCHAR(10000))";
                INSERT_INTO_TABLE = "INSERT INTO MainCharacterDialogs VALUES (?,?)";

                createDbTable(CREATE_TABLE);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterDialogs WHERE CharacterMoment = 'J[1]'";
                paramList.clear();
                paramList.add("J[1]");
                paramList.add("J:La porto subito via da qui.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterDialogs WHERE CharacterMoment = 'J[2]'";
                paramList.clear();
                paramList.add("J[2]");
                paramList.add("J:Il nucleare? Vogliono far passare lo sterminio di Hitler in secondo piano?\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterDialogs WHERE CharacterMoment = 'J[3]'";
                paramList.clear();
                paramList.add("J[3]");
                paramList.add("J:Io? Perché non ci vai tu?\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterDialogs WHERE CharacterMoment = 'J[4]'";
                paramList.clear();
                paramList.add("J[4]");
                paramList.add("J:Ahahaha, facciamolo. Da dove iniziamo?\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterDialogs WHERE CharacterMoment = 'J[5]'";
                paramList.clear();
                paramList.add("J[5]");
                paramList.add("J:Solo?! Si rende conto che quella sala sarà la più controllata?!\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterDialogs WHERE CharacterMoment = 'J[6]'";
                paramList.clear();
                paramList.add("J[6]");
                paramList.add("J:Okaay, ma in tutto questo, come entro nella base senza farmi uccidere al primo respiro?\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterDialogs WHERE CharacterMoment = 'J[7]'";
                paramList.clear();
                paramList.add("J[7]");
                paramList.add("J:Ho bisogno di sonniferi. Non ti dispiacerà di certo aiutare un alleato contro il nemico?\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterDialogs WHERE CharacterMoment = 'J[8]'";
                paramList.clear();
                paramList.add("J[8]");
                paramList.add("J:Hey COMRADE! Come procede il lavoro?\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterDialogs WHERE CharacterMoment = 'J[9]'";
                paramList.clear();
                paramList.add("J[9]");
                paramList.add("J:Non farmi ridere! Non penso che il nostro superiore gradirebbe un tale scherzo in questo periodo.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterDialogs WHERE CharacterMoment = 'J[10]'";
                paramList.clear();
                paramList.add("J[10]");
                paramList.add("J:Certamente comrade. La Russia vincerà!\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterDialogs WHERE CharacterMoment = 'J[11]'";
                paramList.clear();
                paramList.add("J[11]");
                paramList.add("J:Il mio rispetto buon uomo. *S beve*\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterDialogs WHERE CharacterMoment = 'J[12]'";
                paramList.clear();
                paramList.add("J[12]");
                paramList.add("J:Devo entrare, ho con me la tessera.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM MainCharacterDialogs WHERE CharacterMoment = 'J[13]'";
                paramList.clear();
                paramList.add("J[13]");
                paramList.add("J:Sono informazioni confidenziali.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                // Comander
                CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ComanderDialogs (CharacterMoment VARCHAR(1024) PRIMARY KEY, Text VARCHAR(10000))";
                INSERT_INTO_TABLE = "INSERT INTO ComanderDialogs VALUES (?,?)";

                createDbTable(CREATE_TABLE);

                READ_FROM_DB = "SELECT CharacterMoment FROM ComanderDialogs WHERE CharacterMoment = 'C[1]'";
                paramList.clear();
                paramList.add("C[1]");
                paramList.add("C:Ben fatto ragazzo. Ora andiamo via da qui! O vuoi controllare in giro? In tal caso ti aspetto all'entrata (A ovest del piano terra)!");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM ComanderDialogs WHERE CharacterMoment = 'C[2]'";
                paramList.clear();
                paramList.add("C[2]");
                paramList.add("C:Ben fatto Jack, poi ti offro del Daniel's.\n" +
                                "Però prima devo informarti di una cosa: Mentre indagavo nel territorio conquistato dai russi, ho raccolto delle informazioni inquietanti.\n"
                                +
                                "La Russia ormai vede il mondo intero come un nemico, e pertanto sta decidendo di usare il repellente nucleare per far fuori ogni singola minaccia che ostacoli ancora il suo obiettivo.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM ComanderDialogs WHERE CharacterMoment = 'C[3]'";
                paramList.clear();
                paramList.add("C[3]");
                paramList.add("C:Eh beh, dobbiamo impedirglielo, oppure sarà la fine per tutti noi. Te la senti?\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM ComanderDialogs WHERE CharacterMoment = 'C[4]'";
                paramList.clear();
                paramList.add("C[4]");
                paramList.add("C:Ti conosco da anni e sei l'unico così pazzo da riuscire in una tale impresa.");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM ComanderDialogs WHERE CharacterMoment = 'C[5]'";
                paramList.clear();
                paramList.add("C[5]");
                paramList.add("C:Ho un contatto, molto abile. Sta lavorando da giorni a un software in grado di disabilitare e cancellare l'intero sistema informatico contenente i codici di accesso per la detonazione delle bombe nucleari.\n"
                                +
                                "Speriamo abbia finito. Ho impostato le coordinate GPS per la sua attuale posizione. Non dista molto da qui. Andiamo.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM ComanderDialogs WHERE CharacterMoment = 'C[6]'";
                paramList.clear();
                paramList.add("C[6]");
                paramList.add("C:Benvenuto nel nostro quartier generale.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM ComanderDialogs WHERE CharacterMoment = 'C[7]'";
                paramList.clear();
                paramList.add("C[7]");
                paramList.add("C:L'alcool non è mai abbastanza. Comunque, ti presento colui che salverà il mondo!\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM ComanderDialogs WHERE CharacterMoment = 'C[8]'";
                paramList.clear();
                paramList.add("C[8]");
                paramList.add("C:Okay basta così. Come procede il lavoro?\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM ComanderDialogs WHERE CharacterMoment = 'C[9]'";
                paramList.clear();
                paramList.add("C[9]");
                paramList.add("C:Sono sicuro che troverai il modo di entrare!\n" +
                                "Comunque mi aspetto che i russi abbiano delle CASSEFORTI NELLA BASE, dove custodiranno i loro beni più preziosi. E' lì che devi cercare.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM ComanderDialogs WHERE CharacterMoment = 'C[10]'";
                paramList.clear();
                paramList.add("C[10]");
                paramList.add("C:A questo ci abbiamo già pensato noi. Esiste un ingresso secondario a cui si può accedere mediante il condotto di ventilazione.\n"
                                +
                                "Troverai un MAGAZZINO dove vi sono divise di ufficiali russi. Ed è qui che inizia il gioco.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                // Informatics
                CREATE_TABLE = "CREATE TABLE IF NOT EXISTS InformaticDialogs (CharacterMoment VARCHAR(1024) PRIMARY KEY, Text VARCHAR(10000))";
                INSERT_INTO_TABLE = "INSERT INTO InformaticDialogs VALUES (?,?)";

                createDbTable(CREATE_TABLE);

                READ_FROM_DB = "SELECT CharacterMoment FROM InformaticDialogs WHERE CharacterMoment = 'I[1]'";
                paramList.clear();
                paramList.add("I[1]");
                paramList.add("I:Non ti vedo in gran forma comandante. Bevuto di nuovo troppo?\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM InformaticDialogs WHERE CharacterMoment = 'I[2]'";
                paramList.clear();
                paramList.add("I[2]");
                paramList.add("I:Benvenuto! So già tutto di te! Dove vai, i tuoi hobby, le tue imprese, la tua vita sociale, il tuo IBAN...\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM InformaticDialogs WHERE CharacterMoment = 'I[3]'";
                paramList.clear();
                paramList.add("I[3]");
                paramList.add("I:Il software è pronto. È tutto qui.\n" +
                                "*immagine chiavetta* Bene, questo è il piano: *immagine cartina base* Basterà solo INSERIRE LA CHIAVETTA IN UN PC NELLA SALA OPERATIVA e farà tutto automaticamente.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM InformaticDialogs WHERE CharacterMoment = 'I[4]'";
                paramList.clear();
                paramList.add("I[4]");
                paramList.add("I:Inoltre, questo ti servirà per disabilitare le telecamere nella CAMERA DI SORVEGLIANZA. *immagine Jammer*\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                // RusMilitar
                CREATE_TABLE = "CREATE TABLE IF NOT EXISTS RusMilitarsDialogs (CharacterMoment VARCHAR(1024) PRIMARY KEY, Text VARCHAR(10000))";
                INSERT_INTO_TABLE = "INSERT INTO RusMilitarsDialogs VALUES (?,?)";

                createDbTable(CREATE_TABLE);

                READ_FROM_DB = "SELECT CharacterMoment FROM RusMilitarsDialogs WHERE CharacterMoment = 'M[1]'";
                paramList.clear();
                paramList.add("M[1]");
                paramList.add("M:Non daremo scampo al nemico. E tu che ci fai da queste parti?\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM RusMilitarsDialogs WHERE CharacterMoment = 'M[2]'";
                paramList.clear();
                paramList.add("M[2]");
                paramList.add("M:Ma certamente, ragazzo. Fai pure.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM RusMilitarsDialogs WHERE CharacterMoment = 'S[1]'";
                paramList.clear();
                paramList.add("S[1]");
                paramList.add("S:Mi hai fatto prendere un colpo. Per poco non facevo partire l'allarme!\n" +
                                "Non dovresti entrare così all'improvviso oppure potremmo scambiarti per un nemico.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM RusMilitarsDialogs WHERE CharacterMoment = 'S[2]'";
                paramList.clear();
                paramList.add("S[2]");
                paramList.add("S:Da mesi siamo sul fronte notte e giorno. Anche questa volta la Russia si rialzerà!\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM RusMilitarsDialogs WHERE CharacterMoment = 'S[3]'";
                paramList.clear();
                paramList.add("S[3]");
                paramList.add("S:Renditi utile come puoi ragazzo: portami qualcosa CHE MI TENGA SVEGLIO.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM RusMilitarsDialogs WHERE CharacterMoment = 'S[4]'";
                paramList.clear();
                paramList.add("S[4]");
                paramList.add("S:Era ora! *dà il caffè*\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM RusMilitarsDialogs WHERE CharacterMoment = 'S[5]'";
                paramList.clear();
                paramList.add("S[5]");
                paramList.add("S:C'è qualcosa di strano in questo caffè, a me piace zuccherato! *si addormenta*\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM RusMilitarsDialogs WHERE CharacterMoment = 'G2[1]'";
                paramList.clear();
                paramList.add("G2[1]");
                paramList.add("G2:Salve signore, cosa le serve?\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM RusMilitarsDialogs WHERE CharacterMoment = 'G2[2]'";
                paramList.clear();
                paramList.add("G2[2]");
                paramList.add("G2:Motivazione?\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);

                READ_FROM_DB = "SELECT CharacterMoment FROM RusMilitarsDialogs WHERE CharacterMoment = 'G1[1]'";
                paramList.clear();
                paramList.add("G1[1]");
                paramList.add("G1:Piantala, non essere così severo. Ha la tessera, è uno di noi. Entri pure.\n");
                initAndCheck(READ_FROM_DB, INSERT_INTO_TABLE, paramList, columnsNum);
        }
}
