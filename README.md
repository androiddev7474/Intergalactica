

# spelprojekt HT 2018 Nackademin - (v. 38 - 43)

## SPELIDÈ

Året är 3466 e.kr och jorden har blivit obeboelig pga en överhettad jord och fullt med krig mellan olika civilisationer. Några få överlevande lyckas lämna planeten i sista stund med en rymdfarkost utvecklad med den senaste tekniken. Den kan färdas över ljusets hastighet och har ett utstakat mål - stjärnan Utopia med tillhörande planetsystem. I utopia råder total harmoni mellan civilisationerna och krig har aldrig existerat.
.<br>

![alt text](http://fininfo.se/images/spaceshooter.jpg)


-------------------------------------------------------------------------------------------------------------------

## API

Spelet är programmerat i OpenGL ES 3.0 och scenen projiceras orthografiskt. 

-------------------------------------------------------------------------------------------------------------------

## PROGRAMMERING

Koden grundar sig på ECS (Entity Component System) i huvudsak men använder även mönster som Object-Pooling (bra vid exempelvis allokering av fiender och skott) och FactoryPattern (generera Actors och Components). 

------------------------------------------------------------------------------------------------------------------------

## TID

Då tiden har varit mycket begränsad och tekniken (ramverket) avancerat finns spelet endast på prototypnivå - en begränsad uppsättning fiender och 3 nivåer. Men med tanke på att koden är baserad på ECS så är det relativt lätt att bygga vidare på den "ryggrad" som finns och med tiden lansera spelet på Google Play.

## SCREENSHOTS

###### NIVÅKARTA

Varje level rör sig likt en spiral in mot stjärnan Utopia. Nivåkartan visas innan varje nivå.

![alt text](http://fininfo.se/images_/20190217-115255_Intergalactica.jpg)

2) Nivå 1 (spelet har börjat - inga fiender syns än)

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



