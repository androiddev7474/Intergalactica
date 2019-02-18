

# SPELPROJEKT HT 2018 (v. 38 - 43, Nackademin)

## SPELIDÈ

Året är 3466 e.kr. och jorden har blivit obeboelig pga en överhettad jord och fullt med krig mellan olika civilisationer. Några få överlevande lyckas lämna planeten i sista stund med en rymdfarkost utvecklad med den senaste tekniken. Den kan färdas över ljusets hastighet och har ett utstakat mål - stjärnan Utopia med tillhörande planetsystem. I utopia råder total harmoni mellan civilisationerna och krig har aldrig existerat.
.<br>

![alt text](http://fininfo.se/images/spaceshooter.jpg)


## SPELTYP, STIL OCH MÅLGRUPP

###### SPELTYP
Spelet är ett typiskt *shoot 'em up spel* i 2D - ett skepp som färdas i rymden och konfronteras med diverse monster som det försöker skjuta ner.

###### GRAFISK STIL SAMT KÄNSLA
Cartoonish.

###### MÅLGRUPP
Alla som tycker om lite barnsliga spel med action i högt tempo.

-------------------------------------------------------------------------------------------------------------------

## API

Spelet är programmerat i Java/Android och använder ramverket OpenGL ES 3.0. Scenen projiceras orthografiskt - således 2D. 

-------------------------------------------------------------------------------------------------------------------

## PROGRAMMERING

Koden grundar sig på ECS (Entity Component System) i huvudsak men använder även mönster som *Object Pool Pattern* (bra vid exempelvis allokering av fiender och skott) och *Factory Pattern* (generera Actors och Components). Vidare tillämpas *dynamisk bindning* (polymorfism) på Levelklasserna - Level1, Level2 osv ärver gemensam central kod av basklassen Level men bestämmer själva hur de abstrakta metoderna draw() och update() ska se ut. På så sätt kan man bygga en level lite som man själv vill utan att påverka övrig kod.



------------------------------------------------------------------------------------------------------------------------

## TID

Då tiden har varit mycket begränsad och tekniken (ramverket) avancerat finns spelet endast på prototypnivå - en begränsad uppsättning fiender och 3 nivåer. Men med tanke på att koden är baserad på ECS så är det relativt lätt att bygga vidare på den "ryggrad" som finns och med tiden lansera spelet på Google Play.

## SCREENSHOTS

###### NIVÅKARTA

Varje level rör sig likt en spiral in mot stjärnan Utopia. Nivåkartan visas innan varje nivå.

![alt text](http://fininfo.se/images_/20190217-115255_Intergalactica.jpg)

###### YTTRE RYMDEN (NIVÅ 1 - spelet har börjat, inga fiender syns än)

![alt text](http://fininfo.se/images_/20190217-115306_Intergalactica.jpg)

###### YTTRE RYMDEN (NIVÅ 1)

**Batbrains - första attacken**

Batbrains som ser ut att vara mycket slöa - rör sig horisontell långsamt, men kan göra mycket plötsliga utfall söderut mot skeppet.
Se upp, de kan vara mycket aggressiva :-o

![alt text](http://fininfo.se/images_/20190217-115310_Intergalactica.jpg)

###### YTTRE RYMDEN (NIVÅ 1)

**Batbooger - andra attacken**

Batbooger kan ha olika attackmönster beroende på var man befinner sig i spelet - i början rör de sig långsamt mot skeppet men är de på humör kan de attackera blixtsnabbt.

![alt text](http://fininfo.se/images_/20190217-115339_Intergalactica.jpg)

###### DJUNGELPLANETEN (NIVÅ 3)

Skeppet har nu nått den beryktade djungelplaneten där 90% av planetens yta täcks av regnskog. Här återfinns diverse monster.

![alt text](http://fininfo.se/images_/20190217-115414_Intergalactica.jpg)

###### DJUNGELPLANETEN (NIVÅ 3)

**Batbrains attack**


![alt text](http://fininfo.se/images_/20190217-115417_Intergalactica.jpg)

