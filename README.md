# DateParser
fast date identifcation algoritham. Does not uses regex and is easy on CPU. 

Visit http://coffeefromme.blogspot.com/2015/10/utility-to-extract-date-from-text-with.html for implementation details. 

Use with maven 

        <dependency>
            <groupId>net.rationalminds</groupId>
            <artifactId>DateParser</artifactId>
            <version>1.4</version>
        </dependency>

Supported Date formats

DD[]MM[MMM][]YY[YY]

MM[MMM][]DD[]YY[YY]

YY[YY][]MM[MMM][]DD

Supported Date Time formats

DD[]MM[MMM][]YY[YY]{}HH[24]:mm:ss[.SSS][][AM|PM]

MM[MMM][]DD[]YY[YY]{}HH[24]:mm:ss[.SSS][][AM|PM]

MM[MMM][]DD,[]YY[YY]{}HH[24]:mm:ss[.SSS][][AM|PM]

YY[YY][]MM[MMM][]DD{}HH[24]:mm:ss[.SSS][][AM|PM]


*[] Delimiter like / \ - _ . | whitescape etc

*{} Delimiter  _ T whitescape 
