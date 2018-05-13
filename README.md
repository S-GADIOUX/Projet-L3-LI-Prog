# Projet-L3-LI-Prog

Bonjour, ceci est le ReadMe du projet : *Le Jeu de GEENSON / Une introduction aux word embeddings* demandé pour le cours de programmation de 2018 de la licence Linguistique Informatique de Paris 7.

1. Explication rapide du projet
2. Consigne d'utilisation
3. Remarques diverses

## 1. Explication rapide du projet
Le projet consiste à développer un jeu de plateau assez simple.
Les joueurs lancent un dé, avancent sur le plateau, résolvent les effets des cases sur lesquelles ils tombent puis l'ordinateur leur demande de deviner un mot.
Les joueurs vont alors donner des mots indices et l'ordinateur va calculer la liste des mots liés sémantiquement aux mots indices.
Si le mot à deviner fait parti des mots les plus lier sémantiquement, le joueur rejoue. Le but est d'aller au bout du plateau.

## 2. Consigne d'utilisation
Le projet doit d'abord être compilé avec la commande **javac**.
Ensuite il suffit d'exécuter la commande **java Main** et de suivre les instructions données à l'écran.
Les options d’exécution pour le programme sont :
- --w2v \<path\> : indique le chemin vers le fichier de vecteurs de mots ( nécessaire à l’exécution )
- --nbJoueurs \<int\> : indique le nombre de joueurs ( par défault 1 )
- --nbTry \<int\> : indique le nombre d’essais pour chaque tour ( par défault 3 )
- --k \<int\> : le nombre de k-réponses à retourner par le système ( par défault 10 )
- --dé magique \<True/False\>: le jeu fonctionnera avec un dé magique ( par défault non )

## 3. Remarques diverses
- Je n'ai pas choisi d'implémentation optimale en terme d'algorithmique lors du stockage des mots les plus proches. J'ai préféré une implémentation naïve plus simple à comprendre.
- J'ai préféré limiter le nombre d'effet de case pour le joueur à deux : un effet quand il part ( bien qu'aucun n'ai été codé, la structure est présente ) et un lors de son premier atterrissage sur une case, pour éviter de subir des enchaînement d'effets.
- Le plateau est généré aléatoirement à chaque partie.
- Il peut y avoir plusieurs joueurs par case.
- Actuellement le nombre de cases qui font reculer le joueur représente environ 10% du plateau, celles qui le font rejouer, 15%. Il s'agit des seuls éléments non-modifiable via des paramètres. 
