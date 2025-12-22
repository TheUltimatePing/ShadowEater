## Yet Another wplace image converter (but in java)
The basics features works \
features :
- it convert in the 63 WPlace colors
- basic dithering system
- naive black and white conversion
- make a color selector option
- ability to zoom in the image in the app

Next feature :
- dithering (optimized)
- make a scale option
- add the  support for transparent pixels
- download in another folder
- option to separate image in chunk of size X
- know the quantity of each color when converted
- optimize the speed of the converter
- optimize the space taken in memory by the image
- add multi-language support

how to run the app :
- need java 25
- in a terminal run : java -jar shadowEater.jar

A question ? Check the [wiki](https://github.com/TheUltimatePing/ShadowEater/wiki) or ask a question on the [discord]()

/!\ not working :
exe command : jpackage --input .\out\artifacts\shadowEater_jar\ --main-jar shadowEater.jar --main-class com.shadowEater.ShadowApp --name test_app --type exe --app-version 0.0.0 --add-modules ALL-MODULE-PATH  --win-console
