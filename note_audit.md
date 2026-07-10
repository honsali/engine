
## Note d'arbitrage après revue

Cette note précise le statut des points qui n'ont pas été traités immédiatement. Elle ne remet pas en cause les constats techniques de l'audit ; elle explicite seulement le choix de priorité pour un moteur maintenu par un développeur unique et destiné à un usage personnel.

### Point traité dans ce cycle

- **Échappement des chaînes i18n TypeScript** : traité avec `dev.cruding.engine.flow.TsLiteral`. Les labels restent stockés comme données brutes dans `Context.addLabel`, puis sont échappés au moment de l'émission TypeScript par `FeI18nPrinter` et les injections d'actions.

### Points volontairement non traités à court terme

- **API d'indentation `L____` / `L________` et limite de `Component.indent[]`** : le problème est réel mais peu prioritaire. Le modèle courant ne dépasse pas la profondeur concernée, et une correction propre serait un refactoring de confort sans bénéfice fonctionnel immédiat.

- **Deux modèles de mutabilité (`Field` copy-on-write, `Action`/`Component` mutables)** : accepté comme convention interne du DSL. Le risque principal apparaît si une même `Action` ou un même `Component` est réutilisé dans plusieurs emplacements ; aucun cas bloquant n'a motivé une correction immédiate. Une future correction utile serait d'ajouter des garde-fous de réutilisation plutôt que de réécrire toute l'API.

- **Champs publics partout** : acceptable pour un DSL personnel et contrôlé. La priorité, si le moteur doit être durci, serait plutôt une passe de validation du modèle avant génération. Encapsuler massivement les champs Java aurait un coût élevé et peu d'intérêt si le DSL migre un jour vers un langage plus adapté.

- **Bilinguisme moteur anglais / sortie française** : assumé comme style du projet. Le point serait important pour une équipe ou une bibliothèque publique, mais il n'est pas bloquant pour un outil personnel.

- **Documentation complète absente** : volontairement non priorisée. Pour ce projet, les notes factuelles minimales dans `AGENTS.md` suffisent davantage qu'une documentation utilisateur complète.

### Dette technique restante à garder visible

Ces points n'ont pas été écartés ; ils restent les plus rentables si le moteur doit être fiabilisé :

1. Ajouter des **golden-master tests** sur la génération RH.
2. Rendre les **échecs d'écriture fatals** dans `Printer.printFile`.
3. Corriger les **contrats Java** (`Page.compareTo`, `Action.equals/hashCode`, `Element.equals/hashCode`) et les petits bugs mécaniques.
4. Ajouter une **validation agrégée du modèle** avant génération.
5. Nettoyer le **code mort, les `println` de debug et les itérations non triées**.
