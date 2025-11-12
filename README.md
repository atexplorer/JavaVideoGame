# Encounter Handler
* Set up Spring Boot
  * Set up application.properties file
* Set up H2 database
  * Get H2 added to pom.xml
  * Set up properties for h2 database
* Create entity classes for h2 database
  * Monster
    * id
    * Name
    * Type
    * Stats
    * meleeAttackModifier
    * spellAttackModifier
    * SpellSaveDC
    * MeleeAttack Array
    * MagicAttack Array
  * MonsterMeleeAttack
    * id
    * monsterId (used to connect to monster)
    * MeleeAttackId
    * Number of uses per round
  * MonsterMagicAttack
    * id
    * monsterId (used to connect to monster)
    * MagicAttackId
    * Number of total uses
  * MeleeAttack
    * id
    * damageType
    * magical
    * numberOfDice
    * diceSize
  * MagicAttack
    * id
    * SpellName
    * SpellDescription
    * numberOfDice
    * diceSize
    * ToHit
    * HalfDamageOnSave
  * Create test data
  * Create controller methods to handle different requests
  * Create basic front end to show returned data
