## Projektrapport

### Syfte

Dokumentets syfte är i enlighet med uppgiftskraven att visa hur projektet använder sig av objektorienterad design
och objektorienterade principer. Dokumentet inkluderar även reflektioner över fördelar och nackdelar med de OOP koncept som
implementerats.

### Metod

För att uppnå syftet listades relevanta src-paket och dess entiteter i obestämd ordning.
Entiteterna beskrevs övergripande utifrån sin användning i projektet, och bearbetades därefter med dokumentets syfte som
vägledning.
Varje paket tilldelades en sammanfattande reflektion vilket slutligen utgjorde ett underlag för en sammanställd
reflektion av projektet som helhet.

### com.example.rpsgame.dto

**Användning**

- Detta paket används för att formatera spelstatistik som JSON.

**JsonFormatProvider & JsonPrintingProvider**

- Dessa utgör interfaces för -impl* klasserna.

**JsonFormatProviderImpl & JsonPrintingProviderImpl***

- Dessa klasser implementerar interfaces för att möjliggöra formatering och utskrift av JSON.

**Reflektion**

- De interfaces som används ger en abstrakt representation av vad som förväntas finnas i projektets JSON metoder.
Samtidigt så är använda interface specifika för just för JSON,
vilket begränsar dess användbarhet i andra klasser. Det kan exempelvis vara problematiskt
att utföra enhetstester på printStyledJson i JsonPrintingProviderImpl,
eftersom den är beroende av statiska metoder. Uppdelningen av formatering och utskrift resulterade förvisso i en del extra kod,
men
syftar till att tillämpa "Single Responsibility Principle" (SRP) från SOLID-principerna.

### com.example.rpsgame.entity.characters

**Användning**

- Detta paket används för att skapa spelkaraktärer, såsom spelaren, dator-motståndare samt mer specifika variationer av
datormotståndare.

**GameCharacter**

- Denna abstrakta klass används eftersom det finns gemensam funktionalitet i dess underklasser.
Den möjliggör enkel förlängning av funktioner genom att använda arv (inheritance) och har ett avgränsat ansvarsområde.
Klassen använder sig även av mönstret "Observer Pattern" för att spela in de val som spelare gör, vilket utgör
underlaget för spelets statistiksammanställning.

**PlayerCharacter**

- Denna klass ärver från GameCharacter utan att tillföra någon ytterligare funktionalitet. Arvet möjliggör inspelning av
spelarens drag.

**ComputerCharacter**

- Denna klass ärver likväl från GameCharacter.
Den injicerar även dependencies såsom choiceGenerator.

**NameBasedComputerCharacter & TimeBasedComputerCharacter**

- Dessa är subklasser till ComputerCharacter, vilket möjliggör mer specifik funktionalitet relaterat till hur dessa
dator-motståndare gör sina val (Sten, sax, påse).
Båda använder polymorfism genom att implementera generateComputerChoice() var för sig.

**GameCharacterFactory**

- Denna klass använder sig av "Factory Method Pattern" och abstraherar bort processen för att skapa nya spelkaraktärer.
Klassen använder även generiska metoder för att underlätta skapandet av olika typer av karaktärer.

**Reflektion**

- Paketet använder sig av OOP-koncept som arv (inheritance) och polymorfism,
liksom "Observer", "Factory Method" och "Dependency Injection" pattern.
Användningen av OOP underlättade verkligen inspelning av alla karaktärers val i spelet, jag tycker särskilt om att
använda dependency injection
för att underlätta decoupling i koden.

### com.example.rpsgame.entity.choices

**Användning**

- Detta paket används för att bestämma tillgängliga val i spelet samt för att planera och implementera slumpmässiga val för
dator-motståndarna.

#### Choice

- Deklarerar tillgängliga val som en Enum, vilket används på flera ställen i projektet. Det säkerställer att alla
val utgår enbart från de värden som är tillgängliga (ROCK, PAPER & SCISSORS).

#### ChoiceGenerator

- Detta interface bestämmer vilka metoder som ska finnas tillgängliga för att möjliggöra dator-motståndarnas
val och spelstrategi (beroende på typ av motståndare).

#### ChoiceGeneratorImpl

- Denna klass implementerar interfacet av de olika metoderna för att generera val.

**Reflektion**

- Paketet använder sig av OOP-principer genom Enums, Interface och tillhörande implementation. Återanvändningen av Choice
som enum i projektet motverkar kod-duplicering och säkerställer specificitet, vilket jag tycker är fördelaktigt i jämförelse
med att till exempel använda olika String-värden.
De interface som används borde även underlätta om man i framtiden vill lägga till fler
motståndare med nya spelstrategier.

### com.example.rpsgame.entity.rules

**Användning**

- Detta paket används för att definiera spelets regler och tillhörande logik, vilket samtliga övriga klasser måste
förhålla sig till.

#### Result

- En enum som definierar möjliga resultat i spelet (TIE, PLAYER_WIN, COMPUTER_WIN).

#### Rules

- Denna klass använder sig av Result och Choice som en del av sin spellogik. Den använder även ett Singleton Pattern för
att säkerställa att det enbart finns en instans, det vill säga en uppsättning av regler i spelet.

**Reflektion**

- Jag uppfattar paketet som tydligt och enkelt att använda i resten av projektet. Jag upplever att regel klassen var särskilt lämplig för användning av ett Singleton Pattern.

### com.example.rpsgame.entity.stats

**Användning**

- Detta paket används för att möjliggöra insamling av spelstatistik, till exempel olika spelkaraktärers mest återkommande
val utifrån en procentuell fördelning och en sammanställning av matchresultaten.

#### ChoiceObserver & ChoiceRecorder

- ChoiceObserver är ett interface som specificerar vilka metoder som måste implementeras för att kunna spela in en
spelkaraktär; ChoiceRecorder implementerar ChoiceObserver.

#### MatchObserver & MatchRecorder

- MatchObserver är ett interface som specificerar metoder för inspelning av matcher; MatchRecorder implementerar
MatchObserver.

#### RecordedGameCharacter & RecordedMatch

- Dessa används för att skapa objekt av den data som är av intresse när en spelare respektive en match spelas in.

#### StatisticsProvider & StatisticsProviderImpl

- StatisticsProvider är ett interface som även innehåller default för att säkerställa att alla implementationer utför samma
statistikberäkningar; StatisticsProviderImpl implementerar StatisticsProvider.

**Reflektion**

- Jag försökte att efterlikna kodens interfaces och implementationer för att öka tydligheten. I projektet som helhet
så var detta paketet svårast att skriva, mestadels pga utmaningar relaterade till Observer Pattern; samtidigt så var observers
till stor hjälp för statistiken.

### com.example.rpsgame

**Användning**

- Detta paket innehåller själva Game och Menu-klasserna som implementerar och möjliggör att man kan spela spelet.

#### Game

- Game hanterar spellogiken, till exempel spelkaraktärer, och håller reda på spelade rundor och matcher samt resultaten av
dessa.

#### Menu

- Menu instansierar Game och hanterar användarinteraktionen med spelet i terminalen.

**Reflektion**

- Jag försökte få till en gedigen decoupling mellan game och menu för att kunna återanvända game med andra menyer, och var sammantaget nöjd med resultatet.

## Sammanfattande reflektion

Jag anser att projektet implementerar objektorienterade principer och designmönster för att hantera spelets logik och
användargränssnitt.

### Exempel på objektorienterade principer:

**Abstraktion**: den abstrakta klassen GameCharacter används för att definiera gemensam funktionalitet för olika
  spelkaraktärer.

**Inheritance**: Subklasser såsom NameBasedComputerCharacter och TimeBasedComputerCharacter ärver funktionalitet från
ComputerCharacter, vilket möjliggör olika beteenden baserat på typen av datormotståndare.

**Polymorfism**: generateComputerChoice är implementerad i olika datormotståndarklasser och ger flexibilitet i hur motståndarnas spelstrategi
  genereras.

**Interface Implementation**: JsonFormatProvider och ChoiceObserver definierar kontrakt som olika klasser implementerar
  för att tillhandahålla funktionalitet.

### Exempel på användning av designmönster:

**Observer Pattern**: Används för att möjliggöra inspelning av spelarens och datorns val i ChoiceRecorder och
  MatchRecorder, vilket i sin tur möjliggör spelets statistiksammanställning.

**Singleton Pattern**: Används i Rules-klassen för att säkerställa att det bara finns en instans av regler i spelet.

**Factory Method Pattern**: Används i GameCharacterFactory för att underlätta skapandet av spelkaraktärer.

**Dependency Injection Pattern**: Används i stora delar av koden, klasser hämtar sina dependencies från externa källor och
  behöver således inte skapa denna funktionalitet själva.

**Single Responsibility Pattern**: Används i JsonFormatProvider & JsonPrintingProvider för att skilja på ansvar vad gäller
  formatering och utskrift.

### Fördelar:

**Återanvändbarhet**: Objektorienterad design kan möjliggöra en strukturerad och återanvändbar kodbas. Exempelvis kan olika
  typer av spelkaraktärer enkelt läggas till genom att skapa nya subklasser till ComputerCharacter.

**Tydlighet och Struktur**: OOP bidrar till skapar struktur och tydlighet, vilket kan underlätta förståelsen och
  underhållandet av koden över tid.

**Decoupling**: Genom att använda designmönster som Dependency Injection minimeras kopplingen mellan
  klasser, vilket gör koden mer flexibel och lättare att modifiera.

### Nackdelar:

- Det kan ta längre tid att producera kod med OOP i jämförelse med funktionell programmering.
- Det kan vara otympligt att arbeta med designmönster i ett litet projekt som detta. Vissa designmönster medför en del extra kod trots ökad tydlighet.

Sammanfattningsvis så hoppas jag att det framgår hur projektet använt och gynnats av objektorienterade koncept och designmönster, samt hur OOP
har använts för att uppfylla uppgiftskraven.
