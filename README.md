# FinalProjectIN205

A Savoir :
- nous avons considéré que le champ dateRetour de la table Emprunt symbolisait la date à laquelle le livre doit être rendu. (on considère que tous les utilisateurs respectent ces dates, sinon il faudrait créer une 2e date et vérifier que'ils ne sont pas en retard et gérer les cas d'exception et d'erreur liés à cela)

- Nous n'avons pas réussi à tester nos classes à partir de l'exercice 2. En effet, le compilateur ne parvenait pas à trouver le package 'org.h2.jdbcx'.
L'erreur exacte est la suivante :
```./com/excilys/librarymanager/persistence/ConnectionManager.java:6: error: package org.h2.jdbcx does not exist```
```import org.h2.jdbcx.JdbcDataSource;```

h2 a pourtant bien été installé sur nos pc.

- Les lignes de code utilisées dans le terminal pour tester sont les suivantes :
Exercice 1 : ```javac com/excilys/librarymanager/test/TestModele.java com/excilys/librarymanager/modele/*.java```
Exercice 2 : ```javac com/excilys/librarymanager/test/TestDao.java com/excilys/librarymanager/modele/*.java com/excilys/librarymanager/dao/*.java com/excilys/librarymanager/dao/impl/*.java com/excilys/librarymanager/exception/DaoException.java com/excilys/librarymanager/persistence/ConnectionManager.java ```
Exercice 3 : ```javac com/excilys/librarymanager/test/TestService.java com/excilys/librarymanager/modele/*.java com/excilys/librarymanager/dao/*.java com/excilys/librarymanager/dao/impl/*.java com/excilys/librarymanager/exception/DaoException.java com/excilys/librarymanager/persistence/ConnectionManager.java ```

