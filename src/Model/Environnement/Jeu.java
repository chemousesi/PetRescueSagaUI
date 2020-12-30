package Model.Environnement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Jeu {
    /// ajouter des retouches ici une fois les niveaux sont implementés.
    private static Niveau niveau[] = new Niveau[3];
    private static int nbNiveaux = 3;
    private static ArrayList<Joueur> joueurs;
    private static int nbJoueurs = 1;
    private static Parametres parameteresDuJeu;
    /// utilisé dans toutes les lectures.
    private static Scanner sc = new Scanner(System.in);

    public static void lancerJeu() {/// elle fait la configuration nécessaire pour lancer le jeu.
        telechargerNiveaux();
        telechargerJoueurs();
        telechargerParametresDuJeu();
    }

    private static Partie lancerPartie(Joueur joueur) {
        if (joueur.getniveauActuel() > nbNiveaux)
            return null;
        return new Partie(niveau[joueur.getniveauActuel() - 1], joueur);
    }

    public static void joueurEnModeConsole() throws UserNotFound, CloneNotSupportedException {
        welcome();
        String[] premiersChoix = { "1- Connexion", "2- Inscription", "3- Quitter" };
        boolean exit = false;
        while (!exit) {
            int choix_1 = menuTextuelle(premiersChoix, "Menu Principale");
            Joueur joueur = null;
            switch (choix_1) {
                case 1:/// connexion
                    System.out.println("--> Identification");
                    System.out.print("votre pseudo : ");
                    String nomUser = sc.next();
                    joueur = connexion(nomUser);
                    System.out.println("----------> Connexion <----------");
                    System.out.println(joueur);
                    System.out.println("---------------------------------");
                    break;
                case 2:/// inscription
                    System.out.println("--> Création d'un nouveau joueur");
                    System.out.print("Saisir votre nom : ");
                    String nom = sc.next();
                    sc.nextLine();
                    Joueur joueurTemp = null;
                    boolean notAdded = false;
                    String nomTemp = "";
                    do {
                        System.out.print("Saisir votre nom d'utilisateur : ");
                        nomTemp = sc.nextLine();
                        joueur = new Joueur(nom, nomTemp);
                        if (joueurs.contains(joueurTemp)) {
                            System.out.println("*** Joueur existant changer le nom d'utilisateur ***");
                        } else
                            notAdded = true;
                    } while (!notAdded);
                    joueur = creerJoueur(nom, nomTemp);
                    System.out.println("----------> inscription <----------");
                    System.out.println(joueur);
                    System.out.println("-----------------------------------");
                    break;
                case 3:/// quitter
                    sauvegarderJoueurs();/// sauvegarder les joueurs.
                    System.exit(0);
                    break;
            }
            if (joueur == null)
                throw new UserNotFound();
            String[] deuxiemeChoix = { "1- Jouer", "2- Historique", "3- Help", "4- Deconnexion" };
            boolean deconnecter = false;
            while (!deconnecter) {
                int choix_2 = menuTextuelle(deuxiemeChoix, "Mon Compte");
                switch (choix_2) {
                    case 1:/// jouer.
                        Partie partie = lancerPartie(joueur);
                        if (partie == null) {
                            System.out.println("*** Le jeu est terminé ***");
                            String[] reessayer = { "1- OUI", "2- NON" };
                            int choix_3 = menuTextuelle(reessayer, "Recommencer ?");
                            if (choix_3 == 1) {
                                joueur.setniveauActuel(1);
                            }
                        } else {
                            partie.jouerUnePartieModeTexte();
                        }
                        break;
                    case 2:/// historique.
                        System.out.println(
                                "###################################### HISTORIQUE ######################################\n");

                        joueur.afficherHistoriqueConsole();
                        System.out.println(
                                "\n########################################################################################");
                        break;
                    case 3:/// affichage d'un manuel.
                        help();
                        break;
                    case 4: /// deconnexion.
                        System.out.println("Deconnxion du compte.");
                        deconnecter = true;
                        break;
                }
            }
        }
    }

    public static void help() {
        System.out.println(
                "###################################### Aide & Informations ######################################\n");
        System.out.println("1- Pour séléctionner une case à detruire indiquez juste le n° de la ligne et colonne.");
        System.out.println("2- Pour utiliser un missile indiquez le n° de la colonne à detruire.");
        System.out.println("3- Pour utiliser une bombe sélectionnez la case par son n° de ligne et colonne.");
        System.out.println(
                "4- Une bombe détruit la case sélectionnée avec les deux cases au dessus et en dessous horizentalement.");
        System.out.println("5- Indice vous donne les coordonnées de la meilleure case à detruire.");
        System.out.println("6- Le score est calculé par la formule : 2 ^ nombre de cases détruites.");
        System.out.println(
                "\n#################################################################################################");
    }

    private static void telechargerNiveaux() {
        ObjectInputStream reader;
        File dirNiveaux = new File("src/Model/Niveaux");
        int i = 0;
        for (String path : dirNiveaux.list()) {
            try {
                reader = new ObjectInputStream(new FileInputStream("src/Model/Niveaux/" + path));
                niveau[i] = (Niveau) reader.readObject();
                i++;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (i != nbNiveaux) {
            dirNiveaux.delete();
            Jeu.sauvegarderNiveau(
                    new Niveau(1, new Plateau(Plateau.plateauNiveau1()), new Conditions(2, 60), new Aide(0, 0, 0)));

            Jeu.sauvegarderNiveau(
                    new Niveau(2, new Plateau(Plateau.plateauNiveau2()), new Conditions(3, 260), new Aide(1, 1, 0)));
            Jeu.sauvegarderNiveau(
                    new Niveau(3, new Plateau(Plateau.plateauNiveau3()), new Conditions(4, 412), new Aide(1, 0, 1)));
            telechargerNiveaux();
        }
    }

    public static void sauvegarderNiveau(Niveau niveau) {
        final String niv = "Niveau";
        ObjectOutputStream writer;
        try {
            String path = "src/Model/Niveaux/" + niv + String.valueOf(niveau.getNumero()) + ".txt";
            writer = new ObjectOutputStream(new FileOutputStream(path));
            writer.writeObject(niveau);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void telechargerJoueurs() {
        joueurs = new ArrayList<>();
        ObjectInputStream reader;
        File dirJoueurs = new File("src/Model/Joueurs");
        for (String path : dirJoueurs.list()) {
            try {
                reader = new ObjectInputStream(new FileInputStream("src/Model/Joueurs/" + path));
                joueurs.add((Joueur) reader.readObject());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        nbJoueurs = joueurs.size();
    }

    public static void sauvegarderJoueurs() {
        final String joueur = "Joueur";
        ObjectOutputStream writer = null;

        try {
            for (int i = 1; i <= nbJoueurs; i++) {
                String path = "src/Model/Joueurs/" + joueur + String.valueOf(i) + ".txt";
                writer = new ObjectOutputStream(new FileOutputStream(path));
                writer.writeObject(joueurs.get(i - 1));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void telechargerParametresDuJeu() {
        // explain here

    }

    private static void welcome() {
        System.out.println(
                "========================================> Bienvenus dans Pet Rescue saga <========================================");
        // affichage du plateau
    }

    private static int menuTextuelle(String[] tabDeChoix, String nomMenu) { /// amelioration du menu.
        // premier menu du jeu
        System.out.println("____________________________________________| " + nomMenu
                + " |____________________________________________\n");
        for (String choix : tabDeChoix) {
            System.out.println("\t\t\t\t\t\t" + choix + "\n");
        }
        int choix = 0;
        do {
            System.out.print("Tapez votre choix : ");

            try {
                choix = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
                System.err.println("*** Caractère non numérique détecté ***");
                choix = 0;
            }
        } while (choix < 1 || choix > tabDeChoix.length);
        System.out.println(
                "___________________________________________________________________________________________________________");
        return choix;
    }

    public static Joueur creerJoueur(String nom, String nomUser) {
        Joueur joueur = new Joueur(nom, nomUser);
        joueurs.add(joueur);
        nbJoueurs++;
        return joueur;
    }

    public static Joueur connexion(String nomUser) {

        for (Joueur joueur : joueurs) {
            if (joueur.getNomUtilisateur().equals(nomUser)) {
                return joueur;
            }
        }
        return null;
    }

    public static boolean containsJoueurByUserName(String uName) {
        for (Joueur joueur : joueurs) {
            if (joueur.getNomUtilisateur().equals(uName))
                return true;
        }
        return false;

    }
}
